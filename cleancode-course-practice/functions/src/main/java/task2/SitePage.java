package task2;

import java.util.Map;


public class SitePage {

    private static final String HTTP = "http://";
    private static final String EDITABLE = "/?edit=true";
    private static final String DOMAIN = "mysite.com";


    private String siteGroup;
    private String userGroup;
    private Map<String, String> params;

    public SitePage(String siteGroup, String userGroup) {
        this.siteGroup = siteGroup;
        this.userGroup = userGroup;
    }

    public void setParams(Map<String, String> params){
        this.params = params;
    }

    public String getEditablePageUrl() {
        return buildUrl();
    }

    private String buildUrl() {
        String paramsString = getParamsFromKeyValuePair();
        return HTTP + DOMAIN + EDITABLE + paramsString + getAttributes();
    }

    private String getParamsFromKeyValuePair() {
        StringBuilder paramsString = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            paramsString.append(buildParamsString(param));
        }
        return paramsString.toString();
    }

    private StringBuilder buildParamsString(Map.Entry<String, String> param) {
        return new StringBuilder().append("&").append(param.getKey()).append("=").append(param.getValue());
    }

    private String getAttributes() {
        return "&siteGrp=" + getSiteGroup() + "&userGrp=" + getUserGroup();
    }

    public String getUserGroup() {
        return userGroup;
    }

    public String getSiteGroup() {
        return siteGroup;
    }




}
