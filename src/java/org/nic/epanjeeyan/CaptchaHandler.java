/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nic.epanjeeyan;

import java.util.Random;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rahul
 */
public class CaptchaHandler {
    public void setCaptcha(HttpServletRequest request) {
        Random r = new Random();
        int ran1 = r.nextInt(9);
        int ran2 = r.nextInt(9);
        request.setAttribute("ran1", ran1);
        request.setAttribute("ran2", ran2);

        request.getSession().setAttribute("ran1", ran1);
        request.getSession().setAttribute("ran2", ran2);
    }

}
