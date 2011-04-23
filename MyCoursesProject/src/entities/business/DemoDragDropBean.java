package entities.business;

import java.util.ArrayList;
import java.util.List;

import org.richfaces.component.Dropzone;
import org.richfaces.event.DragEvent;
import org.richfaces.event.DropEvent;
import org.richfaces.event.DropListener;

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
	
	private List<Course> containerCME = new ArrayList<Course>();
	private List<Course> containerMSI = new ArrayList<Course>();
	
	public DemoDragDropBean() {
		super();
		
		types.add("PHP");
		types.add("JAVA");
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
}