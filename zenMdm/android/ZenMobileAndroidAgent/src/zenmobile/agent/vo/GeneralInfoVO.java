package zenmobile.agent.vo;



import java.util.Date;



public class GeneralInfoVO {



    private String agentVersion;

    private Date agentInstallDate;

    private Date lastRecordedDate;

    private String agentPolicy;

    

    public GeneralInfoVO() {

        

    }



    public String getAgentVersion() {

        return agentVersion;

    }



    public void setAgentVersion(String agentVersion) {

        this.agentVersion = agentVersion;

    }



    public Date getAgentInstallDate() {

        return agentInstallDate;

    }



    public void setAgentInstallDate(Date agentInstallDate) {

        this.agentInstallDate = agentInstallDate;

    }



    public Date getLastRecordedDate() {

        return lastRecordedDate;

    }



    public void setLastRecordedDate(Date lastRecordedDate) {

        this.lastRecordedDate = lastRecordedDate;

    }



    public String getAgentPolicy() {

        return agentPolicy;

    }



    public void setAgentPolicy(String agentPolicy) {

        this.agentPolicy = agentPolicy;

    }

    

    

}
