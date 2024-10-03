package mazegame.entity;

import java.util.HashMap;

public class Location {
	private HashMap exits;
	private String description;
	private String label;
	
	public Location () { }
	public Location (String description, String label){
		this.setDescription(description);
		this.setLabel(label);
		exits = new HashMap();
	}
	public boolean addExit (String exitLabel, Exit theExit){
		if (exits.containsKey(exitLabel))
			return false;
		exits.put(exitLabel, theExit);
		return true;
	}
	public Exit getExit(String exitLabel){
		return (Exit) exits.get(exitLabel);
	}	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}	
	public String availableExits() {
	        StringBuilder returnMsg = new StringBuilder();
	        for (Object label: this.exits.keySet()) {
	            returnMsg.append("[" + label.toString() + "] ");
	        }
	        return returnMsg.toString();
	}
	
	public String toString() {
	        return "**********\n" + this.label + "\n**********\n"
	            + "Exits found :: " + availableExits() + "\n**********\n" 
	            + this.description + "\n**********\n";
	}
	
	public boolean containsExit(String exitLabel) {
		return exits.containsKey(exitLabel);
    }

}
