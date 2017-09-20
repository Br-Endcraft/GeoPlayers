package me.jonasxpx.geoplayers;

public enum RegionCodes {
	
	BR("Brazil"),
	US("United States"),
	PH("Philippines"),
	PE("Peru"),
	PT("Portugal"),
	AR("Argentina"),
	CH("China"),
	AU("Australia"),
	PL("Pol�nia");
	private String name;
	
	private RegionCodes(String name) {
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static RegionCodes getRegionFromString(String code){
		switch(code){
		case "CA":
			return RegionCodes.US;
		case "PH":
			return RegionCodes.PH;
		case "BR":
			return RegionCodes.BR;
		case "PE":
			return RegionCodes.PE;
		case "PT":
			return RegionCodes.PT;
		case "AR":
			return RegionCodes.AR;
		case "CH":
			return RegionCodes.CH;
		case "AU":
			return RegionCodes.AU;
		case "PL":
			return RegionCodes.PL;
		default:
			return RegionCodes.US;
		}
	}
	
}
