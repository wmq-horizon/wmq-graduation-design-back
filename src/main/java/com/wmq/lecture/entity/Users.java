package com.wmq.lecture.entity;

public class Users {
    private String uid;
    private String name;
    private String password;
    private Integer integrity;
    private String role;
    private Double score;
    private Integer status;

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.name
     *
     * @param name the value for users.name
     *
     * @mbg.generated Fri Feb 05 17:57:09 CST 2021
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.password
     *
     * @return the value of users.password
     *
     * @mbg.generated Fri Feb 05 17:57:09 CST 2021
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.password
     *
     * @param password the value for users.password
     *
     * @mbg.generated Fri Feb 05 17:57:09 CST 2021
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.integrity
     *
     * @return the value of users.integrity
     *
     * @mbg.generated Fri Feb 05 17:57:09 CST 2021
     */
    public Integer getIntegrity() {
        return integrity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.integrity
     *
     * @param integrity the value for users.integrity
     *
     * @mbg.generated Fri Feb 05 17:57:09 CST 2021
     */
    public void setIntegrity(Integer integrity) {
        this.integrity = integrity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.role
     *
     * @return the value of users.role
     *
     * @mbg.generated Fri Feb 05 17:57:09 CST 2021
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.role
     *
     * @param role the value for users.role
     *
     * @mbg.generated Fri Feb 05 17:57:09 CST 2021
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.score
     *
     * @return the value of users.score
     *
     * @mbg.generated Fri Feb 05 17:57:09 CST 2021
     */
    public Double getScore() {
        return score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.score
     *
     * @param score the value for users.score
     *
     * @mbg.generated Fri Feb 05 17:57:09 CST 2021
     */
    public void setScore(Double score) {
        this.score = score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.status
     *
     * @return the value of users.status
     *
     * @mbg.generated Fri Feb 05 17:57:09 CST 2021
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.status
     *
     * @param status the value for users.status
     *
     * @mbg.generated Fri Feb 05 17:57:09 CST 2021
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}