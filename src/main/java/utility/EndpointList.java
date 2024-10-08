package utility;

public class EndpointList {
	public static final String DEVPPOBMETAV3 = "https://ids-fe1.com/PPOBmeta/webresources/v3";
	public static final String STGPPOBMETAV3 = "https://private-staging.ids.id/PPOB/webresources";
	public static final String DEVPPOBMETA = "https://ids-fe1.com/PPOBmeta/webresources";
	public static final String STGRESTPROJECTDANA = "https://private-staging.ids.id/IDSRESTProjectDana/webresources";
	public static final String APIPROJECT = "http://117.54.12.141:8080/APIProject/webresources";
	public static final String APIPROJECTTIM = "http://117.54.12.141:8080/apiprojecttim/webresources";
	public static final String MOCK = "http://117.54.12.146:3070";
	public static final String APIPROJECTSTG = "https://private-staging.ids.id/IDSAPIProject/webresources";


	public static String getEndpoint(String endpointName) {
		switch (endpointName) {
			case "devppobmetav3":
				return DEVPPOBMETAV3;
			case "stgppobmetav3":
				return STGPPOBMETAV3;
			case "devppobmeta":
				return DEVPPOBMETA;
			case "stgrestprojectdana":
				return STGRESTPROJECTDANA;
			case "apiproject":
				return APIPROJECT;
			case "apiprojecttim":
				return APIPROJECTTIM;
			case "apiprojectstg":
				return APIPROJECTSTG;
			case "mock":
				return MOCK;
			default:
				throw new IllegalArgumentException("Invalid endpoint name: " + endpointName);
		}
	}
}
