import badboy.com.model.Address;
import badboy.com.model.Department;
import badboy.com.model.Employee;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class TestJPA {

	EntityManagerFactory entityManagerFactory = null;

	@Before
	public void before() {
		entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit");
	}

	@Test
	public void addEmployee() {

		Employee employee = new Employee();
		employee.setFirstName("aaa");
		employee.setLastName("bbb");
		employee.setBirthDate(new Date());
		employee.setPhone("123456");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	@Test
	public void deleteAllEmployee() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.createNamedQuery("deleteAllEmployee").executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
	}


	@Test
	public void addEmployeeWithAddress() {

		Address address = new Address();
		address.setAddressDetail("ddd");

		Employee employee = new Employee();
		employee.setFirstName("aaa");
		employee.setLastName("bbb");
		employee.setBirthDate(new Date());
		employee.setPhone("123456");
		employee.setAddress(address);

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	@Test
	public void removeEmployeeWithAddress() {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Employee employee1 = (Employee) entityManager.createNamedQuery("findEmployeeByName").setParameter("firstName","aaa").getSingleResult();
		entityManager.remove(employee1);

		entityManager.getTransaction().commit();
		entityManager.close();

	}

	@Test
	public void addAddressWithEmployee() {

		Address address = new Address();
		address.setAddressDetail("eee");

		Employee employee = new Employee();
		employee.setFirstName("aaa");
		employee.setLastName("bbb");
		employee.setBirthDate(new Date());
		employee.setPhone("123456");

		address.setEmployee(employee);

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(address);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	@Test
	public void addDepartment() {
		Department department = new Department();
		department.setDepartmentName("GOOGLE");

		Employee employee1 = new Employee();
		employee1.setFirstName("qqq");
		employee1.setLastName("www");
		employee1.setBirthDate(new Date());
		employee1.setPhone("123456789");
		employee1.setDepartment(department);

		Employee employee2 = new Employee();
		employee2.setFirstName("www");
		employee2.setLastName("qqq");
		employee2.setBirthDate(new Date());
		employee2.setPhone("987654321");
		employee2.setDepartment(department);

		Set<Employee> employeeSet = new HashSet<Employee>();
		employeeSet.add(employee1);
		employeeSet.add(employee2);

		department.setEmployees(employeeSet);

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(department);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	@Test
	public void deleteDepartmentWithEmployee() {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Department  department = (Department) entityManager.createNamedQuery("findDepartmentByName").setParameter("departmentName","GOOGLE").getSingleResult();
		entityManager.remove(department);
		entityManager.getTransaction().commit();
		entityManager.close();


	}




}
