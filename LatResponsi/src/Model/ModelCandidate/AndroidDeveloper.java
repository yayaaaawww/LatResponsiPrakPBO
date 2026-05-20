/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ModelCandidate;

/**
 *
 * @author LENOVO
 */
public class AndroidDeveloper extends ModelCandidate implements Recruitable {
    private static final float MIN_SCORE = 85.0f;

    public AndroidDeveloper(String nama, int writing, int coding, int interview) {
        setNama(nama);
        setPath("Android Dev");
        setWriting(writing);
        setCoding(coding);
        setInterview(interview);
        setScore(calculateScore());
        setStatus(determineStatus());
    }

    @Override
    public float calculateScore() {
        return (getWriting() + getCoding() + getInterview()) / 3.0f;
    }

    @Override
    public String determineStatus() {
        return calculateScore() >= MIN_SCORE ? "DITERIMA" : "TIDAK DITERIMA";
    }
}
