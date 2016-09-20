package comtrue.homework.domain;

public class MemberVO {
String name,level,email,phone,num;
int updatenum;
public int getUpdatenum() {
	return updatenum;
}
public void setUpdatenum(int updatenum) {
	this.updatenum = updatenum;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getLevel() {
	return level;
}
public void setLevel(String level) {
	this.level = level;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public int getDbnum() {
	return Integer.parseInt(num);
}
public void setNum(String num) {
	this.num = num+"";
}
public String getNum() {
	return num;
}

@Override
public String toString() {
	return "MemberVO [name=" + name + ", level=" + level + ", email=" + email + ", phone=" + phone + ", num=" + num
			+ ", updatenum=" + updatenum + "]";
}


}
