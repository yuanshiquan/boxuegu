package cn.edu.gdmec.android.boxuegu.bean;

public class ExercisesBean {
    public int id;//每章习题id
    public String title;//每章习题标题
    public String content;//每章习题数目
    public int background;//每章习题前面的序号背景
    public int subjectId;//每道习题的id
    public String subject;//每道习题的题干
    public String a;//每道题的A选项
    public String b;//每道题的B选项
    public String c;//每道题的C选项
    public String d;//每道题的D选项
    public int answer;//每道题的正确答案
    public int select;//用户选中的那项（0表示选对了，1表示A错，2.......）
}
