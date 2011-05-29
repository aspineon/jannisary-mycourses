package entities.utility;

import java.util.ArrayList;

public class ProblematicSpots {
	private ArrayList<Integer> problematicSpots = new ArrayList<Integer>();
	private int blockedSpot;
	
	public ProblematicSpots() {
		super();
	}
	
	public ProblematicSpots(int pBlockedSpot, ArrayList<Integer> pProblematicSports) {
		super();
		this.blockedSpot = pBlockedSpot;
		this.problematicSpots = pProblematicSports;
	}
	
	public ProblematicSpots(ProblematicSpots psItem) {
		super();
		this.blockedSpot = psItem.blockedSpot;
		this.problematicSpots = psItem.problematicSpots;
	}
	
	public ProblematicSpots(int blockedSpot, int credit) {
		super();
		this.blockedSpot = blockedSpot;
		int startPoint = this.blockedSpot + 1;
		int limit = this.blockedSpot + credit;
		for(int i = startPoint; i < limit; i++) {
			Integer item = new Integer(i);
			this.problematicSpots.add(item);
		}
	}
	
	@Override
	public String toString() {
		String retStr = "\n";
		retStr = retStr + "Blocked Spot: " + Integer.toString(this.blockedSpot) + "\n";
		retStr = retStr + "Problematic Spots: ";
		for(int i = 0; i < this.problematicSpots.size(); i++) {
			retStr = retStr + Integer.toString(this.problematicSpots.get(i));
		}
		retStr = retStr + "\n";
		return  retStr;
	}
	
	public boolean isProblematicByProblematicSpot(Integer problem) {
		if(this.problematicSpots.contains(problem)) {
			return true;
		}
		return false;
	}
	
	public boolean isProblematicByBlockedSpot(int pBlockedSpot) {
		if(this.blockedSpot == pBlockedSpot) {
			return true;
		}
		return false;
	}
	
	public int getBlockedSpotByProblem(Integer problem) {
		if(this.problematicSpots.contains(problem)) {
			return this.blockedSpot;
		}
		return 0;
	}
	
//**************************************************************************************************	
	public ArrayList<Integer> getProblematicSpots() {
		return problematicSpots;
	}

	public void setProblematicSpots(ArrayList<Integer> problematicSpots) {
		this.problematicSpots = problematicSpots;
	}

	public int getBlockedSpot() {
		return blockedSpot;
	}

	public void setBlockedSpot(int blockedSpot) {
		this.blockedSpot = blockedSpot;
	}
}
