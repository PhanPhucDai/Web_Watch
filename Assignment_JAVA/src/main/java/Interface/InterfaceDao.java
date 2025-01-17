package Interface;

import java.util.List;

public interface InterfaceDao<T> {
	  void addItems(T item);
	  boolean deleteById(T id);
	  List<T> getAllItems();
	  T getItemById(String id);


}
