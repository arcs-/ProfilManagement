package biz.stillhart.profileManagement.controller;

import biz.stillhart.face.model.FaceImage;
import biz.stillhart.profileManagement.model.Student;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;


/**
 * Created by Patrick Stillhart on 10/31/14.
 */
@ManagedBean
@RequestScoped
public class ProfileBean implements Serializable {

    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;

    private Student student;
  //  private Student studentSource;

    private Part profilePicture;

    @PostConstruct
    public void init() {
        student = sessionBean.getStudent();
       // student = studentSource.copy();
    }

    public void upload() {
        try {
            BufferedImage bufferedProfile = ImageIO.read(profilePicture.getInputStream());
            FaceImage face = new FaceImage(bufferedProfile);

            if(face.foundFace()) {

                face.setNoCropMultiplier(1);
                face.setAdditionPadding(20);
                face.setDimension(128,128);
                BufferedImage profilePicture = face.getScaledProfileFace();

                // ToDo: Check jpg error
                File outputfile = new File("/home/benutzer/Downloads/Profil/out/artifacts/Profil_war_exploded/profile/" + student.getUserName() + ".png");
                ImageIO.write(profilePicture, "png", outputfile);

                student.setProfilePicturePath("profile/" + student.getUserName() + ".png");

                save();
            }

        } catch (IOException e) {
            System.err.println("something went wrong " + e.getMessage());
        }

    }

    public void save() {
       // studentSource.update(student);

        sessionBean.getDataBaseBean().getDataBase().save(student);

        // ToDO: save to DB
    }

    public void cancel() {

    }

    /*
      JSF Stuff
     */

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public Part getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Part profilePicture) {
        this.profilePicture = profilePicture;
    }
}
