package com.example.test1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Source {

@SerializedName("fi101")
@Expose
private List<Fi101> fi101 = null;
@SerializedName("mid_")
@Expose
private String mid;
@SerializedName("iv107")
@Expose
private String iv107;
@SerializedName("iv104")
@Expose
private String iv104;
@SerializedName("id")
@Expose
private String id;
@SerializedName("type")
@Expose
private String type;
@SerializedName("user")
@Expose
private String user;
@SerializedName("when")
@Expose
private String when;
@SerializedName("fi102")
@Expose
private List<Fi102> fi102 = null;
@SerializedName("seen")
@Expose
private Integer seen;
@SerializedName("clicked")
@Expose
private Integer clicked;
@SerializedName("iv105")
@Expose
private String iv105;

public List<Fi101> getFi101() {
return fi101;
}

public void setFi101(List<Fi101> fi101) {
this.fi101 = fi101;
}

public String getMid() {
return mid;
}

public void setMid(String mid) {
this.mid = mid;
}

public String getIv107() {
return iv107;
}

public void setIv107(String iv107) {
this.iv107 = iv107;
}

public String getIv104() {
return iv104;
}

public void setIv104(String iv104) {
this.iv104 = iv104;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public String getUser() {
return user;
}

public void setUser(String user) {
this.user = user;
}

public String getWhen() {
return when;
}

public void setWhen(String when) {
this.when = when;
}

public List<Fi102> getFi102() {
return fi102;
}

public void setFi102(List<Fi102> fi102) {
this.fi102 = fi102;
}

public Integer getSeen() {
return seen;
}

public void setSeen(Integer seen) {
this.seen = seen;
}

public Integer getClicked() {
return clicked;
}

public void setClicked(Integer clicked) {
this.clicked = clicked;
}

public String getIv105() {
return iv105;
}

public void setIv105(String iv105) {
this.iv105 = iv105;
}

}