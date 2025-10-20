package expense_tracker.services;

import java.time.LocalDate;
import java.util.List;

public interface CRUDService<T> {
    List<T> list();
    void add(LocalDate date, String description, Float amount);
    void delete(Long id);
    void update(Long id, LocalDate date, String description, Float amount);

}
