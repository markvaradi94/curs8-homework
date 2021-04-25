package ro.fasttrackit.curs8homework.repository;

import org.springframework.stereotype.Repository;
import ro.fasttrackit.curs8homework.model.entity.Room;
import ro.fasttrackit.curs8homework.model.entity.RoomFacilities;
import ro.fasttrackit.curs8homework.model.filters.RoomFilters;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Repository
public class RoomDao {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public RoomDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }

    public List<Room> getAll(RoomFilters filters) {
        CriteriaQuery<Room> criteria = criteriaBuilder.createQuery(Room.class);
        Root<Room> room = criteria.from(Room.class);
        Join<Room, RoomFacilities> facilities = room.join("facilities");
        List<Predicate> whereClause = buildPredicates(filters, room, facilities);
        CriteriaQuery<Room> query = criteria.select(room).where(whereClause.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

    private List<Predicate> buildPredicates(RoomFilters filters, Root<Room> root, Join<Room, RoomFacilities> facilities) {
        List<Predicate> predicates = new ArrayList<>();

        ofNullable(filters.getNumber())
                .ifPresent(number -> predicates.add(criteriaBuilder.equal(root.get("number"), number)));
        ofNullable(filters.getFloor())
                .ifPresent(floor -> predicates.add(criteriaBuilder.equal(root.get("floor"), floor)));
        ofNullable(filters.getHotelName())
                .ifPresent(hotelName -> predicates.add(criteriaBuilder.like(root.get("hotelName"), hotelName)));
        ofNullable(filters.getTv())
                .ifPresent(tv -> predicates.add(criteriaBuilder.equal(facilities.get("tv"), tv)));
        ofNullable(filters.getDoubleBed())
                .ifPresent(doubleBed -> predicates.add(criteriaBuilder.equal(facilities.get("doubleBed"), doubleBed)));

        return predicates;
    }
}
