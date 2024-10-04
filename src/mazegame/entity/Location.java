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
	    StringBuilder exitsDisplay = new StringBuilder("Available exits:\n");
	    StringBuilder shopExits = new StringBuilder("Shops nearby:\n");

	    // Separate exits into regular locations and shops
	    for (Object key : this.exits.keySet()) {
	        Exit exit = (Exit) exits.get(key);
	        if (exit.getDestination() instanceof Shop) {
	            shopExits.append("[").append(key.toString()).append("] to ").append(exit.getDestination().getLabel()).append("\n");
	        } else {
	            exitsDisplay.append("[").append(key.toString()).append("] to ").append(exit.getDestination().getLabel()).append("\n");
	        }
	    }

	    // Combine regular exits and shop exits in the final display
	    return "**********\n" + this.label + "\n**********\n" +
	           "Exits found:\n" + exitsDisplay.toString() +
	           (shopExits.length() > "Shops nearby:\n".length() ? shopExits.toString() : "No shops nearby.\n") +
	           "**********\n" + this.description + "\n**********\n";
	}

	
	public boolean containsExit(String exitLabel) {
		return exits.containsKey(exitLabel);
    }

}
