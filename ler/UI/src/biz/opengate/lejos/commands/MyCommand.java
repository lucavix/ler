package biz.opengate.lejos.commands;

import biz.opengate.lejos.ui.LejosController;

public abstract class MyCommand {
	public String image;
	public String description;
	
	public MyCommand() {
	}
	
	

	public MyCommand(String image,String description) {
		super();
		this.image = image;
		this.description = description;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyCommand other = (MyCommand) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}
	
	public abstract void execute(LejosController lejosController);
	
}
