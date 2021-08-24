package repository;

import moduls.Product;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface IProductRepo extends PagingAndSortingRepository<Product,Long> {
}
