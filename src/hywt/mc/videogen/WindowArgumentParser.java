package hywt.mc.videogen;

public class WindowArgumentParser extends Window{
	
	public static void parse() {
		Project.pname=pnameField.getText();
		Project.pdescription=dtrpnPackagedescription.getText();
		
		
		int[] facing;
		if(getRdbtnPositiveX().isSelected()) {
    		facing= RefStrings.positiveX;
    	}else if(getRdbtnNegativeX().isSelected()) {
    		facing= RefStrings.negativeX;
    	}else if(getRdbtnPositiveZ().isSelected()) {
    		facing= RefStrings.positiveZ;
    	}else{
    		facing= RefStrings.negativeZ;
    	}
		Project.facing=facing;
		
		
		Project.sizex=Double.parseDouble(getSizeX().getText());
		Project.sizey=Double.parseDouble(getSizeY().getText());
		
		Project.mode=getRdbtnHoverAtTarget().isSelected();
		
		Project.txtx=Window.getTxtX().getText();
		Project.txty=Window.getTxtY().getText();
		Project.txtz=Window.getTxtZ().getText();
	}
	
	
}
