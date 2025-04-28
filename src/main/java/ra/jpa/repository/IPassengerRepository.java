package ra.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.jpa.model.entity.Passenger;
import ra.jpa.model.entity.PassengerGender;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IPassengerRepository extends JpaRepository<Passenger, String> {
    //  - Lấy tất cả hành khách có tên chứa một chuỗi con nhất định (không phân biệt hoa thường).
    @Query("select P from Passenger P where P.name like concat('%',:name,'%') ")
    List<Passenger> searchPassengersByName(@Param("name") String name);

    //  - Lấy tất cả hành khách sinh sau một ngày cụ thể.
    @Query("select P from Passenger P where P.birthDay > :date")
    List<Passenger> searchPassengersWithBirthDayAfter(@Param("date") LocalDate date);

    //  - Lấy ra top N hành khách có tên dài nhất.
    @Query("select P from Passenger P order by length(P.name) DESC LIMIT :n")
    List<Passenger> searchPassengersTopNameLength(@Param("n") int n);

    //  - Lấy ra tất cả hành khách có email kết thúc bằng một domain cụ thể.
    @Query("select P from Passenger P where P.email like concat('%',:reg) ")
    List<Passenger> searchPassengersWithEmailEndWith(@Param("reg") String reg);

    //  - Đếm số lượng hành khách theo giới tính.
    @Query("select count(P.id) from Passenger P where P.gender = :gender ")
    long countPassengerByGender(@Param("gender") PassengerGender gender);

    //  - Lấy ra hành khách có email dài nhất.
    @Query("select P from Passenger P order by length(P.email) DESC LIMIT 1")
    Optional<Passenger> findPassengerMaxlengthEmail();
//  - Lấy ra tất cả hành khách có số điện thoại bắt đầu bằng một chuỗi số cho trước.

    //  - Lấy ra tên của tất cả hành khách, viết hoa chữ cái đầu của mỗi từ.
    @Query("select CONCAT(UPPER(SUBSTRING(P.name, 1, 1)), LOWER(SUBSTRING(P.name, 2))) FROM Passenger P")
    List<Passenger> findPassengerUpperCase();

    //  - Kiểm tra xem có hành khách nào có email trùng với một email cho trước không (trả về boolean).
    @Query("Select count(P.id)>0 From Passenger P where P.email = :email")
    boolean existsByEmail(@Param("email") String email);

    //  - Lấy ra danh sách hành khách và sắp xếp theo ngày sinh, với người lớn tuổi nhất ở đầu danh sách.
    @Query("select P from Passenger P order by P.birthDay asc")
    List<Passenger> findAllWithAgeOlder();
}
