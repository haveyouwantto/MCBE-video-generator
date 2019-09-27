package hywt.mc.videogen;

import java.io.File;

public class WindowArgumentParser extends Window{
	
	public static Project parse() {
		Project p=new Project();
		p.setPname(pnameField.getText());
		p.setPdescription(dtrpnPackagedescription.getText());
		
		p.setSource(new File(getSrcpath().getText()));
		p.setOutput(new File(getDestpath().getText()));
		
		int facing = 0;
		if(getRdbtnPositiveX().isSelected()) {
    		facing= 0;
    	}else if(getRdbtnNegativeX().isSelected()) {
    		facing= 1;
    	}else if(getRdbtnPositiveZ().isSelected()) {
    		facing= 2;
    	}else if(getRdbtnNegativeZ().isSelected()){
    		facing= 3;
    	}
		p.setFacing(facing);
		
		
		p.setSizex(Double.parseDouble(getSizeX().getText()));
		p.setSizey(Double.parseDouble(getSizeY().getText()));
		
		p.setHover(getRdbtnHoverAtTarget().isSelected());
		
		p.setTxtx(Window.getTxtX().getText());
		p.setTxty(Window.getTxtY().getText());
		p.setTxtz(Window.getTxtZ().getText());
		return p;
	}
	
	public static void update(Project p) {
		pnameField.setText(p.getPname());
		dtrpnPackagedescription.setText(p.getPdescription());
		
		getSrcpath().setText(p.getSource().getPath());
		getDestpath().setText(p.getOutput().getPath());
		
		System.out.print( p.getFacing()==0);
		
		if( p.getFacing()== 0) {
			getRdbtnPositiveX().setSelected(true);
    	}else if(p.getFacing()== 1) {
    		getRdbtnNegativeX().setSelected(true);
    	}else if(p.getFacing()== 2) {
    		getRdbtnPositiveZ().setSelected(true);
    	}else if(p.getFacing()== 3){
    		getRdbtnNegativeZ().setSelected(true);
    	}
		
		getSizeX().setText(String.valueOf(p.getSizex()));
		getSizeY().setText(String.valueOf(p.getSizey()));
		
		if(p.isHover()) {
			getRdbtnHoverAtTarget().setSelected(true);
		}else {
			getRdbtnStatic().setSelected(true);
		}

		
		getTxtX().setText(p.getTxtx());
		getTxtY().setText(p.getTxty());
		getTxtZ().setText(p.getTxtz());
		
	}
	
	
}
