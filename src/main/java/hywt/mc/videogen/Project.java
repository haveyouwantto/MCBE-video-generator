package hywt.mc.videogen;

import java.io.File;

import com.google.gson.Gson;

public class Project {

	private File source=new File(".");
	private File output=new File(".");
	
	private String pname="package_id";
	private String pdescription="Package Description";
	
	private boolean hover=true;
	
	private String txtx="^";
	private String txty="^1.62";
	private String txtz="^3";

	private double sizex=3.5555555555555;
	private double sizey=2;
	
	private int facing=0;
	
	public String toJSON() {
		Gson g=new Gson();
		String s=g.toJson(this);
		return s;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPdescription() {
		return pdescription;
	}

	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}

	public boolean isHover() {
		return hover;
	}

	public void setHover(boolean hover) {
		this.hover = hover;
	}

	public String getTxtx() {
		return txtx;
	}

	public void setTxtx(String txtx) {
		this.txtx = txtx;
	}

	public String getTxty() {
		return txty;
	}

	public void setTxty(String txty) {
		this.txty = txty;
	}

	public String getTxtz() {
		return txtz;
	}

	public void setTxtz(String txtz) {
		this.txtz = txtz;
	}

	public double getSizex() {
		return sizex;
	}

	public void setSizex(double sizex) {
		this.sizex = sizex;
	}

	public double getSizey() {
		return sizey;
	}

	public void setSizey(double sizey) {
		this.sizey = sizey;
	}

	public int getFacing() {
		return facing;
	}

	public void setFacing(int facing) {
		this.facing = facing;
	}

	public File getSource() {
		return source;
	}

	public void setSource(File source) {
		this.source = source;
	}

	public File getOutput() {
		return output;
	}

	public void setOutput(File output) {
		this.output = output;
	}
	
}
