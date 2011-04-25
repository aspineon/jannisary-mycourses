package entities.business;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.richfaces.component.Dropzone;
import org.richfaces.event.DragEvent;
import org.richfaces.event.DropEvent;
import org.richfaces.event.DropListener;
import javax.faces.event.ActionEvent;

import entities.dao.Course;

/**
 * @author $Autor$
 *
 */
public class DemoDragDropBean {

	private List types = new ArrayList();
	
	private Object dragValue;
	
	private Object testParam;
	
	private List<Course> allCourses = null;
	private List<SelectItem> listCourseGrade = new ArrayList<SelectItem>();
	private List<Course> containerCME = new ArrayList<Course>();
	private List<Course> containerMSI = new ArrayList<Course>();
	
	public DemoDragDropBean() {
		super();
		
		listCourseGrade.add(new SelectItem("First Year"));
		listCourseGrade.add(new SelectItem("Second Year"));
		listCourseGrade.add(new SelectItem("Third Year"));
		listCourseGrade.add(new SelectItem("Fourth Year"));
		
		types.add("PHP");
		types.add("JAVA");
	}
	
	public void testAction(ActionEvent ev){
		System.out.println("My event is:" + ev.getComponent().getId());
		System.out.println(ev.getSource().toString());
		
	}
	
	public void processDrop(DropEvent event) {
		System.out.println("Bean.processDrop()");
		this.dragValue = (Course) event.getDragValue();
		Course crs = (Course) dragValue;
		if(crs.getPrecondition().equals("Test")){
			this.containerCME.add(crs);
		}else if(crs.getPrecondition().equals("asd")){
			this.containerMSI.add(crs);
		}
	}
	
	public void processDrag(DragEvent dragEvent) {
		System.out.println("Bean.processDrag()");
	}

	public List getTypes() {
		return types;
	}
	
	public String dragAction() {
		System.out.println("Bean.dragAction()");
		return null;
	}

	public String dropAction() {
		System.out.println("Bean.dropAction()");
		/*Course crs = (Course) dragValue;
		if(crs.getPrecondition().equals("Test")){
			this.containerCME.add(crs);
		}else if(crs.getPrecondition().equals("asd")){
			this.containerMSI.add(crs);
		}*/
		return null;
	}
	
	public List<Course> getAllCourses() {
		Course course = new Course();
		synchronized (this) {
			if (allCourses == null) {
				allCourses = new ArrayList<Course>();
					try {
						allCourses = course.getAllCourses();
						//listSeperator();
					} catch (Exception e) {
						System.out.println("!!!!!!loadAllUsers Error: " + e.getMessage());
						e.printStackTrace();
					}
			}
		}
		return allCourses;
	}
	
	public List<Course> getContainerCME() {
		return containerCME;
	}

	public void setContainerCME(List<Course> containerCME) {
		this.containerCME = containerCME;
	}

	public List<Course> getContainerMSI() {
		return containerMSI;
	}

	public void setContainerMSI(List<Course> containerMSI) {
		this.containerMSI = containerMSI;
	}

	public Object getDragValue() {
		return dragValue;
	}

	public Object getTestParam() {
		return testParam;
	}
	
	public void setTestParam(Object testParam) {
		this.testParam = testParam;
		System.out.println("Bean.setTestParam()" + testParam);
	}

	public List<SelectItem> getListCourseGrade() {
		return listCourseGrade;
	}

	public void setListCourseGrade(List<SelectItem> listCourseGrade) {
		this.listCourseGrade = listCourseGrade;
	}
	
}