package com.example.buysell.controllers;

import com.google.gson.Gson;
import org.apache.juli.logging.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import com.example.buysell.dbfuncs.dbfunctions;
import com.example.buysell.jsonclasses.ResponseBody;
import com.example.buysell.HashPassword;

@RestController
public class Controller1 {
    @GetMapping("/")
    public String getprod() throws SQLException, ClassNotFoundException {
        return "BRO ONLY POST PLEASE";
    }
    @PostMapping("/")
    public String getjson(@RequestBody String body) throws SQLException, ClassNotFoundException, IOException {
        try {
            Gson lol = new Gson();
            ResponseBody event = lol.fromJson(body, ResponseBody.class);
            ResponseBody response = new ResponseBody();
            String mode = event.getMode();
            Gson gson = new Gson();
            Date nowdate = new Date();
            System.out.println(nowdate.toString());
            String[] trvls = dbfunctions.read("travels", new String[]{"driver", "companion", "firstadr", "secondadr", "price", "date", "countpass"});
            String[] dates = trvls[5].split(";");
            for (int i = 0; i < dates.length; i++) {
                String dt = dates[i];
                int hrs = 0;
                if (dt.contains("+")) {
                    hrs = -1 * Integer.valueOf(dt.substring(dt.indexOf("+"), dt.indexOf("+") + 3));
                    dt = dt.substring(0, dt.indexOf("+")) + dt.substring(dt.indexOf("+") + 6, dt.length());
                } else if (dt.contains("-")) {
                    hrs = -Integer.valueOf(dt.substring(dt.indexOf("-"), dt.indexOf("-") + 3));
                    dt = dt.substring(0, dt.indexOf("-")) + dt.substring(dt.indexOf("-") + 6, dt.length());
                }
                System.out.println(dates[i]);
                Date traveldate = dbfunctions.addHoursToJavaUtilDate(new Date(dt), hrs);
                System.out.println(nowdate.toString() + " " + traveldate.toString());
                System.out.println(nowdate.after(traveldate));
                if (nowdate.after(traveldate)) {
                    String drver = trvls[0].split(";")[i];
                    String comp = trvls[1].split(";")[i];
                    String fadr = trvls[2].split(";")[i];
                    String sadr = trvls[3].split(";")[i];
                    String prce = trvls[4].split(";")[i];
                    String cntpass = trvls[6].split(";")[i];
                    dbfunctions.deletewhere("travels", "driver='" + drver + "' AND companion='" + comp + "' AND firstadr='" + fadr + "' AND secondadr='" + sadr + "' AND price='" + prce + "' AND countpass='" + cntpass + "' AND date='" + dates[i] + "'");
                    dbfunctions.write("history", new String[]{drver, comp, fadr, sadr, prce, dates[i], cntpass});
                }
            }
            trvls = dbfunctions.read("walkers", new String[]{"fstwalker", "scndwalker", "date", "type", "count", "firstadress", "secondadress"});
            dates = trvls[2].split(";");
            for (int i = 0; i < dates.length; i++) {
                String dt = dates[i];
                int hrs = 0;
                if (dt.contains("+")) {
                    hrs = -1 * Integer.valueOf(dt.substring(dt.indexOf("+"), dt.indexOf("+") + 3));
                    dt = dt.substring(0, dt.indexOf("+")) + dt.substring(dt.indexOf("+") + 6, dt.length());
                } else if (dt.contains("-")) {
                    hrs = -Integer.valueOf(dt.substring(dt.indexOf("-"), dt.indexOf("-") + 3));
                    dt = dt.substring(0, dt.indexOf("-")) + dt.substring(dt.indexOf("-") + 6, dt.length());
                }
                System.out.println(dates[i]);
                Date traveldate = dbfunctions.addHoursToJavaUtilDate(new Date(dt), hrs);
                System.out.println(nowdate.toString() + " " + traveldate.toString());
                System.out.println(nowdate.after(traveldate));
                if (nowdate.after(traveldate)) {
                    String drver = trvls[0].split(";")[i];
                    String comp = trvls[1].split(";")[i];
                    String fadr = trvls[5].split(";")[i];
                    String sadr = trvls[6].split(";")[i];
                    String prce = trvls[3].split(";")[i];
                    String cntpass = trvls[4].split(";")[i];
                    dbfunctions.deletewhere("walkers", "fstwalker='" + drver + "' AND scndwalker='" + comp + "' AND firstadress='" + fadr + "' AND secondadress='" + sadr + "' AND type='" + prce + "' AND count='" + cntpass + "' AND date='" + dates[i] + "'");
                    dbfunctions.write("history", new String[]{drver, comp, fadr, sadr, prce, dates[i], cntpass});
                }
            }
            trvls = dbfunctions.read("adventures", new String[]{"fstwalker", "scndwalker", "date", "type", "count", "firstadress", "secondadress"});
            dates = trvls[2].split(";");
            for (int i = 0; i < dates.length; i++) {
                String dt = dates[i];
                int hrs = 0;
                if (dt.contains("+")) {
                    hrs = -1 * Integer.valueOf(dt.substring(dt.indexOf("+"), dt.indexOf("+") + 3));
                    dt = dt.substring(0, dt.indexOf("+")) + dt.substring(dt.indexOf("+") + 6, dt.length());
                } else if (dt.contains("-")) {
                    hrs = -Integer.valueOf(dt.substring(dt.indexOf("-"), dt.indexOf("-") + 3));
                    dt = dt.substring(0, dt.indexOf("-")) + dt.substring(dt.indexOf("-") + 6, dt.length());
                }
                System.out.println(dates[i]);
                Date traveldate = dbfunctions.addHoursToJavaUtilDate(new Date(dt), hrs);
                System.out.println(nowdate.toString() + " " + traveldate.toString());
                System.out.println(nowdate.after(traveldate));
                if (nowdate.after(traveldate)) {
                    String drver = trvls[0].split(";")[i];
                    String comp = trvls[1].split(";")[i];
                    String fadr = trvls[5].split(";")[i];
                    String sadr = trvls[6].split(";")[i];
                    String prce = trvls[3].split(";")[i];
                    String cntpass = trvls[4].split(";")[i];
                    dbfunctions.deletewhere("walkers", "fstwalker='" + drver + "' AND scndwalker='" + comp + "' AND firstadress='" + fadr + "' AND secondadress='" + sadr + "' AND type='" + prce + "' AND count='" + cntpass + "' AND date='" + dates[i] + "'");
                    dbfunctions.write("history", new String[]{drver, comp, fadr, sadr, prce, dates[i], cntpass});
                }
            }
            if (mode.equals("login")) {
                System.out.println(1);
                String login = event.getNumber().replace(
                        " ", "").replace(
                        "+", "").replace(
                        "(", "").replace(
                        ")", "");
                String password = HashPassword.hashPassword(event.getPassword());
                String[] users = dbfunctions.readwhere("users", new String[]{"number", "password"}, "number='" + login + "' AND password='" + password + "'");
                if (users.length != 0 && !users[0].equals("")) {
                    response.setLogin(true);
                } else {
                    response.setLogin(false);
                }
                return gson.toJson(response);
            } else if (mode.equals("reg")) {
                String login = event.getNumber().replace(
                        " ", "").replace(
                        "+", "").replace(
                        "(", "").replace(
                        ")", "");
                String password = HashPassword.hashPassword(event.getPassword());
                String second_password = HashPassword.hashPassword(event.getSecondPassword());
                String[] otv = dbfunctions.readwhere("users", new String[]{"number"}, "number='" + login + "'");
                if (otv.length != 0 && !otv[0].equals("")) {
                    response.setText("Ваш номер телефона уже зарегистрирован!");
                    response.setReg(false);
                } else {
                    response.setText("Успешная регистрация!");
                    response.setReg(true);
                    dbfunctions.write("users", new String[]{login, password});
                }
                return gson.toJson(response);
            } else if (mode.equals("profile")) {
                String number = event.getNumber();
                String name = event.getName();
                String surname = event.getSurname();
                String patronymic = event.getPatronymic();
                String picture = event.getPicture();
                //            dbfunctions.saveBufferedimage(dbfunctions.base64ToBufferedImage(picture), number + ".png");
                dbfunctions.saveBase64txt(picture, number);
                String ver = "0";
                String[] otv = dbfunctions.readwhere("profiles", new String[]{"number"}, "number='" + number + "'");
                if (otv.length != 0 && !otv[0].equals("")) {
                    dbfunctions.updatewhere("profiles", new String[]{"name", "surname", "patronymic", "verification", "rating"}, new String[]{name, surname, patronymic, ver, "5.0"}, "number='" + number + "'");
                    return gson.toJson(response);
                }
                dbfunctions.write("profiles", new String[]{number, name, surname, patronymic, number + ".txt", ver, "5.0", "empty"});
                return gson.toJson(response);
            } else if (mode.equals("getprofile")) {
                String number = event.getNumber();
                String name = "", sur = "", patr = "", ver = "", rating = "", path = "", token = "";
                String[] otv = dbfunctions.readwhere("profiles", new String[]{"name", "surname", "patronymic", "picture", "verification", "rating", "vktoken"}, "number='" + number + "'");
                if (otv.length != 0 && !otv[0].equals("")) {
                    name = otv[0];
                    sur = otv[1];
                    patr = otv[2];
                    path = otv[3];
                    ver = otv[4];
                    rating = otv[5];
                    token = otv[6];
                }
                String picture = dbfunctions.readFile(path);
                response.setName(name);
                response.setSurname(sur);
                response.setPatronymic(patr);
                response.setVerification(ver);
                response.setRating(rating);
                response.setPicture(picture);
                response.setVkAccessToken(token);
                return gson.toJson(response);
            }
            else if(mode.equals("changepicture"))
            {
                String number = event.getNumber();
                String picture = event.getPicture();
                dbfunctions.saveBase64txt(picture, number);
                dbfunctions.updatewhere("profiles", new String[]{"picture"}, new String[]{number + ".txt"}, "number='" + number + "'");
                return gson.toJson(response);

            }
            else if (mode.equals("ping")) {
                response.setStatus("OK");
            }
            else if (mode.equals("verification")) {
                System.out.println("верификация");
                String number = event.getNumber();
                String token = event.getVkAccessToken();
                dbfunctions.updatewhere("profiles", new String[]{"verification", "vktoken"}, new String[]{"1", token}, "number='" + number + "'");
                return gson.toJson(response);
            } else if (mode.equals("trueverification")) {
                String number = event.getNumber();
                String vktoken = event.getVkAccessToken();
                dbfunctions.updatewhere("profiles", new String[]{"verification", "vktoken"}, new String[]{"2", vktoken}, "number='" + number + "'");
            } else if (mode.equals("puttravels")) {
                String driver = event.getDriver();
                String companion = event.getCompanion();
                String firstadress = event.getFirstadress();
                String secondadress = event.getSecondadress();
                String price = event.getPrice();
                String date = event.getDate();
                String cntpass = event.getCountpass();
                String comm = event.getText();
                String tags = event.getTags();
                dbfunctions.write("travels", new String[]{driver, companion, firstadress, secondadress, price, date, cntpass, comm, tags});
                return gson.toJson(response);
            } else if (mode.equals("gettravels")) {
                String number = event.getNumber();
                String[] otv = dbfunctions.readwhere("travels", new String[]{"driver", "companion", "firstadr", "secondadr", "price", "date", "countpass", "tags"}, "driver='" + number + "' OR companion='" + number + "'");
                response.setDriver(otv[0]);
                response.setCompanion(otv[1]);
                response.setFirstadress(otv[2]);
                response.setSecondadress(otv[3]);
                response.setPrice(otv[4]);
                response.setDate(otv[5]);
                response.setCountpass(otv[6]);
                response.setTags(otv[7]);
                String[] otv_comm = dbfunctions.read_comments("travels");
                response.setText(otv_comm[0]);
                response.setDelimiter(otv_comm[1]);
                return gson.toJson(response);
            } else if (mode.equals("putdriver")) {
                String frstadr = event.getFirstadress();
                String scndadr = event.getSecondadress();
                String date = event.getDate();
                String comp = event.getCompanion();
                String driver = event.getDriver();
                dbfunctions.updatewhere("travels", new String[]{"driver"}, new String[]{driver}, "companion='" + comp + "' AND date='" + date + "' AND firstadr='" + frstadr + "' AND secondadr='" + scndadr + "'");
                return gson.toJson(response);
            } else if (mode.equals("putcompanion")) {
                String frstadr = event.getFirstadress();
                String scndadr = event.getSecondadress();
                String date = event.getDate();
                String comp = event.getCompanion();
                String driver = event.getDriver();
                dbfunctions.updatewhere("travels", new String[]{"companion"}, new String[]{comp}, "driver='" + driver + "' AND date='" + date + "' AND firstadr='" + frstadr + "' AND secondadr='" + scndadr + "'");
                return gson.toJson(response);
            } else if (mode.equals("finddriver")) {
                String[] otv = dbfunctions.readwhere("travels", new String[]{"driver", "firstadr", "secondadr", "price", "countpass", "date", "tags"}, "companion='empty'");
                response.setDriver(otv[0]);
                response.setFirstadress(otv[1]);
                response.setSecondadress(otv[2]);
                response.setPrice(otv[3]);
                response.setCountpass(otv[4]);
                response.setDate(otv[5]);
                response.setTags(otv[6]);
                String[] otv_comm = dbfunctions.read_comments("travels");
                response.setText(otv_comm[0]);
                response.setDelimiter(otv_comm[1]);
                String verif = "";

                for (int i = 0; i < otv[0].split(";").length; i++) {
                    verif = verif + dbfunctions.readwhere("profiles", new String[]{"verification"}, "number='" + otv[0].split(";")[i] + "'")[0] + ";";
                }
                verif = verif.substring(0, verif.length() - 1);
                response.setDriverver(verif);

                return gson.toJson(response);
            } else if (mode.equals("findcompanion")) {
                String[] otv = dbfunctions.readwhere("travels", new String[]{"companion", "firstadr", "secondadr", "price", "countpass", "date", "tags"}, "driver='empty'");
                response.setCompanion(otv[0]);
                response.setFirstadress(otv[1]);
                response.setSecondadress(otv[2]);
                response.setPrice(otv[3]);
                response.setCountpass(otv[4]);
                response.setDate(otv[5]);
                String[] otv_comm = dbfunctions.read_comments("travels");
                response.setText(otv_comm[0]);
                response.setDelimiter(otv_comm[1]);
                response.setTags(otv[6]);
                String verif = "";
                for (int i = 0; i < otv[0].split(";").length; i++) {
                    verif = verif + dbfunctions.readwhere("profiles", new String[]{"verification"}, "number='" + otv[0].split(";")[i] + "'")[0] + ";";
                }
                verif = verif.substring(0, verif.length() - 1);
                response.setCompver(verif);
                return gson.toJson(response);
            } else if (mode.equals("changepass")) {
                String number = event.getNumber();
                String newPassword = event.getPassword();
                String oldPassword = HashPassword.hashPassword(event.getSecondPassword());
                String[] otv = dbfunctions.readwhere("users", new String[]{"password"}, "number='" + number + "'");
                if (otv.length != 0 && !otv[0].equals("")) {
                    if (otv[0].equals(oldPassword)) {
                        dbfunctions.updatewhere("users", new String[]{"password"}, new String[]{HashPassword.hashPassword(newPassword)}, "number='" + number + "'");
                        response.setText("Успешно!");
                        return gson.toJson(response);
                    } else {
                        response.setText("Неправильный пароль");
                        return gson.toJson(response);
                    }
                } else {
                    response.setText("Неправильный номер!");
                }
                return gson.toJson(response);
            } else if (mode.equals("changename")) {
                String number = event.getNumber();
                String newName = event.getName();
                String newSur = event.getSurname();
                String newOtc = event.getPatronymic();
                dbfunctions.updatewhere("profiles", new String[]{"name", "surname", "patronymic"}, new String[]{newName, newSur, newOtc}, "number='" + number + "'");
                return gson.toJson(response);
            } else if (mode.equals("putwalkers")) {
                String number = event.getWalker();
                String second = "empty";
                String date = event.getDate();
                String type = event.getPrice();
                String count = event.getCountpass();
                String firstadress = event.getFirstadress();
                String secondadress = event.getSecondadress();
                String comment = event.getText();
                String tags = event.getTags();
                dbfunctions.write("walkers", new String[]{number, second, date, type, count, firstadress, secondadress, comment, tags});
                return gson.toJson(response);
            } else if (mode.equals("findwalker")) {
                String[] otv = dbfunctions.readwhere("walkers", new String[]{"fstwalker", "date", "type", "count", "firstadress", "secondadress", "tags"}, "scndwalker='empty'");
                response.setWalker(otv[0]);
                response.setDate(otv[1]);
                response.setPrice(otv[2]);
                response.setCountpass(otv[3]);
                response.setFirstadress(otv[4]);
                response.setSecondadress(otv[5]);
                String[] otv_comm = dbfunctions.read_comments("walkers");
                response.setText(otv_comm[0]);
                response.setDelimiter(otv_comm[1]);
                response.setTags(otv[6]);
                String verif = "";
                for (int i = 0; i < otv[0].split(";").length; i++) {
                    verif = verif + dbfunctions.readwhere("profiles", new String[]{"verification"}, "number='" + otv[0].split(";")[i] + "'")[0] + ";";
                }
                verif = verif.substring(0, verif.length() - 1);
                response.setVerification(verif);
                return gson.toJson(response);
            } else if (mode.equals("makewalk")) {
                String number = event.getWalker();
                String second = event.getNumber();
                String date = event.getDate();
                String type = event.getPrice();
                String count = event.getCountpass();
                String firstadress = event.getFirstadress();
                String secondadress = event.getSecondadress();
                dbfunctions.updatewhere("walkers", new String[]{"scndwalker"}, new String[]{second}, "fstwalker='" + number + "' AND date='" + date + "' AND type='" + type + "' AND firstadress='" + firstadress + "' AND secondadress='" + secondadress + "'");
            } else if (mode.equals("getwalks")) {
                String number = event.getNumber();
                String[] otv = dbfunctions.readwhere("walkers", new String[]{"fstwalker", "scndwalker", "date", "type", "firstadress", "secondadress"}, "fstwalker='" + number + "' OR scndwalker='" + number + "'");
                response.setDriver(otv[0]);
                response.setCompanion(otv[1]);
                response.setDate(otv[2]);
                response.setPrice(otv[3]);
                response.setFirstadress(otv[4]);
                response.setSecondadress(otv[5]);
                return gson.toJson(response);
            } else if (mode.equals("putadventure")) {
                String number = event.getWalker();
                String second = "empty";
                String date = event.getDate();
                String type = event.getPrice();
                String count = event.getCountpass();
                String firstadress = event.getFirstadress();
                String secondadress = event.getSecondadress();
                String comment = event.getText();
                String tags = event.getTags();
                dbfunctions.write("adventures", new String[]{number, second, date, type, count, firstadress, secondadress, comment, tags});
                return gson.toJson(response);
            } else if (mode.equals("getadventures")) {
                String number = event.getNumber();
                String[] otv = dbfunctions.readwhere("adventures", new String[]{"fstwalker", "scndwalker", "date", "type", "firstadress", "secondadress"}, "fstwalker='" + number + "' OR scndwalker='" + number + "'");
                response.setDriver(otv[0]);
                response.setCompanion(otv[1]);
                response.setDate(otv[2]);
                response.setPrice(otv[3]);
                response.setFirstadress(otv[4]);
                response.setSecondadress(otv[5]);
                return gson.toJson(response);
            } else if (mode.equals("makeadventure")) {
                String number = event.getWalker();
                String second = event.getNumber();
                String date = event.getDate();
                String type = event.getPrice();
                String count = event.getCountpass();
                String firstadress = event.getFirstadress();
                String secondadress = event.getSecondadress();
                dbfunctions.updatewhere("adventures", new String[]{"scndwalker"}, new String[]{second}, "fstwalker='" + number + "' AND date='" + date + "' AND type='" + type + "' AND firstadress='" + firstadress + "' AND secondadress='" + secondadress + "'");
            } else if (mode.equals("findadventure")) {
                String[] otv = dbfunctions.readwhere("adventures", new String[]{"fstwalker", "date", "type", "count", "firstadress", "secondadress", "tags"}, "scndwalker='empty'");
                response.setWalker(otv[0]);
                response.setDate(otv[1]);
                response.setPrice(otv[2]);
                response.setCountpass(otv[3]);
                response.setFirstadress(otv[4]);
                response.setSecondadress(otv[5]);
                String[] otv_comm = dbfunctions.read_comments("adventures");
                response.setText(otv_comm[0]);
                response.setDelimiter(otv_comm[1]);
                response.setTags(otv[6]);
                String verif = "";
                System.out.println(otv[0]);
                for (int i = 0; i < otv[0].split(";").length; i++) {
                    verif = verif + dbfunctions.readwhere("profiles", new String[]{"verification"}, "number='" + otv[0].split(";")[i] + "'")[0] + ";";
                }
                verif = verif.substring(0, verif.length() - 1);
                response.setVerification(verif);
                return gson.toJson(response);
            } else if (mode.equals("gethistory")) {
                String number = event.getNumber();
                String[] otv = dbfunctions.readwhere("history", new String[]{"driver", "companion", "firstadr", "secondadr", "price", "date"}, "driver='" + number + "' OR companion='" + number + "'");
                response.setDriver(otv[0]);
                response.setCompanion(otv[1]);
                response.setDate(otv[5]);
                response.setPrice(otv[4]);
                response.setFirstadress(otv[2]);
                response.setSecondadress(otv[3]);
                return gson.toJson(response);
            }
            else {
                response.setStatus("error: field \"mode\" is Invalid");
            }
            return gson.toJson(response);
        }
        catch (Exception e)
        {
            Gson lol = new Gson();
            ResponseBody event = lol.fromJson(body, ResponseBody.class);
            ResponseBody response = new ResponseBody();
            System.out.println(e.toString());
            System.out.println(e.getMessage());
            e.printStackTrace();
            response.setText(e.toString());
            return e.toString();
        }
    }
}
