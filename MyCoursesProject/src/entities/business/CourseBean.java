package entities.business;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.richfaces.model.SortField;
import org.richfaces.model.SortOrder;
import org.richfaces.model.selection.SimpleSelection;

import entities.dao.Course;;

public class CourseBean {
	
	public String AddCourse(){
		Course course = new Course();
		course.setCourseCode(courseCode);
		course.AddCourse();
		return null;
	}
	
	private Course course;
	private Integer courseId;
	private String courseCode;
	private String courseName;
	private int teoricLectureHours;
	private int practiceLectureHourse;
	private boolean attendance;
	private int typeofCourseId;
	private String courseDescription;
	
	
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getTeoricLectureHours() {
		return teoricLectureHours;
	}
	public void setTeoricLectureHours(int teoricLectureHours) {
		this.teoricLectureHours = teoricLectureHours;
	}
	public int getPracticeLectureHourse() {
		return practiceLectureHourse;
	}
	public void setPracticeLectureHourse(int practiceLectureHourse) {
		this.practiceLectureHourse = practiceLectureHourse;
	}
	public boolean isAttendance() {
		return attendance;
	}
	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}
	public int getTypeofCourseId() {
		return typeofCourseId;
	}
	public void setTypeofCourseId(int typeofCourseId) {
		this.typeofCourseId = typeofCourseId;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	///////////////////////////////////////////////////////////
	
	private Course currentItem = new Course();

	public void fetchCurrentRow(ActionEvent event) {
		String course_Id=(FacesContext.getCurrentInstance().
				getExternalContext().getRequestParameterMap().get("courseId"));
		currentRow = Integer.parseInt(FacesContext.getCurrentInstance().
				getExternalContext().getRequestParameterMap().get("row"));
		for (Course item : allCourses) {
			if (item.getCourseId().equals(course_Id)){
				currentItem=item;
				break;
			}
		}
	}

	private Set<Integer> keys = new HashSet<Integer>();

	private int currentRow;

	private SimpleSelection selection = new SimpleSelection();

	private SortOrder order = new SortOrder();


//	private ArrayList<CourseBean[]> model = null;

	private ArrayList<Course> selectedCourses = new ArrayList<Course>();
	private ArrayList<Facet> columns = new ArrayList<Facet>();
/*	private static int DECIMALS = 1;
	private static int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;*/

	private List<Course> allCourses = null;

	public void DataTableScrollerBean() {
		//initColumnsHeaders();
		SortField[] fields = { new SortField("courseId", true) };
		order.setFields(fields);
	}

	/*public String getallCourses() {
		synchronized (this) {
			if (allCourses == null) {
				allCourses = new ArrayList<Course>();
				// Buraya allcourses.add() gibi bisey yazilicak ve databaseden cekilen veriler buraya atilacak.
				Course course = new Course();
				allCourses = course.GetAllCourses();
				
			}
		}
		return null;
	}*/

/*	public List<Course> getTenRandomCars() {
		List<Course> result = new ArrayList<Course>();
		int size = getallCourses().size() - 1;
		for (int i = 0; i < 10; i++) {
			result.add(getallCourses().get(rand(1, size)));
		}
		return result;
	}*/

	public SimpleSelection getSelection() {
		return selection;
	}

	public void setSelection(SimpleSelection selection) {
		this.selection = selection;
	}

/*	public String takeSelection() {
		getselectedCourses().clear();
		if (getSelection().isSelectAll()) {
			getselectedCourses().addAll(allCourses);
		} else {
			Iterator<Object> iterator = getSelection().getKeys();
			while (iterator.hasNext()) {
				Object key = iterator.next();
				getselectedCourses().add(allCourses.get((Integer)key));
				
			}
		}
		return null;
	}*/

	public ArrayList<Course> getselectedCourses() {
		return selectedCourses;
	}

	public void setselectedCourses(ArrayList<Course> selectedCourses) {
		this.selectedCourses = selectedCourses;
	}

/*	public void initColumnsHeaders() {
		columns.clear();
		String header;
		String footer = "";
		header = "Chevrolet";
		Facet facet = new Facet(header, footer);
		columns.add(facet);
		header = "Ford";
		facet = new Facet(header, footer);
		columns.add(facet);
		header = "Nissan";
		facet = new Facet(header, footer);
		columns.add(facet);
		header = "Toyota";
		facet = new Facet(header, footer);
		columns.add(facet);
		header = "GMC";
		facet = new Facet(header, footer);
		columns.add(facet);
		header = "Infiniti";
		facet = new Facet(header, footer);
		columns.add(facet);
	}*/

/*	public ArrayList<CourseBean[]> getModel() {
		if (model == null) {
			model = new ArrayList<CourseBean[]>();
			for (int i = 0; i < 9; i++) {
				CourseBean[] items = new CourseBean[6];
				items[0] = createCar("Chevrolet", "Corvette", 1).get(0);
				items[1] = createCar("Ford", "Explorer", 1).get(0);
				items[2] = createCar("Nissan", "Maxima", 1).get(0);
				items[3] = createCar("Toyota", "Camry", 1).get(0);
				items[4] = createCar("GMC", "Yukon", 1).get(0);
				items[5] = createCar("Infiniti", "G35", 1).get(0);
				model.add(items);
			}
		}
		return model;
	}*/

	public ArrayList<Facet> getColumns() {
		return columns;
	}

	public SortOrder getOrder() {
		return order;
	}

	public void setOrder(SortOrder order) {
		this.order = order;
	}

	public Course getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(Course currentItem) {
		this.currentItem = currentItem;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public void store() {
		allCourses.set(currentRow, currentItem);
		keys.clear();
		keys.add(currentRow);
	}

	public void delete() {
		allCourses.remove(currentRow);
	}

	public Set<Integer> getKeys() {
		return keys;
	}

	public void setKeys(Set<Integer> keys) {
		this.keys = keys;
	}
}
