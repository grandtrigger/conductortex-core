/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hackathon.rest.dao;

import br.com.hackathon.rest.model.Evento;
import br.com.hackathon.rest.model.Participante;
import br.com.hackathon.rest.util.ConversorJSON;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 18/02/2017
 *
 */
public class NotificacaoDAO {

    private final String API_KEY = "AIzaSyBkU5BeN3vn5evreBGxhc4kjDtpO1SI01c";

    public void sendNotificacaoConfirmacao(Evento evento) {
        try {
            ConversorJSON conversor = new ConversorJSON(String.class);

            StringBuilder builder = new StringBuilder();

            builder.append(evento.getCriador().getConta().getApelido());
            builder.append(" acaba de lhe convidar para participar do evento ");
            builder.append(evento.getDescricao());
            builder.append(", que irá se realizar no dia ");
            builder.append(evento.getDataCriacao());
            builder.append(".");

            List<String> ids = new ArrayList<>();
            for (Participante participante : evento.getParticipantes()) {
                if (!participante.getConfirmacao()) {
                    ids.add(participante.getConta().getRegistroId());
                }
            }

            String input = "{\"registration_ids\" : [\"" + conversor.converteJSON(ids) + "\"],\"data\" : {\"message\": \"" + builder.toString() + "\"},}";
            send(input);

        } catch (MalformedURLException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendNotificacaoEventoConfirmado(Evento evento) {
        try {
            ConversorJSON conversor = new ConversorJSON(String.class);

            StringBuilder builder = new StringBuilder();

            builder.append("O evento ");
            builder.append(evento.getDescricao());
            builder.append(" foi confirmado por todos e \"VAI ACONTECER\".");

            List<String> ids = new ArrayList<>();
            
            ids.add(evento.getCriador().getConta().getRegistroId());
            for (Participante participante : evento.getParticipantes()) {
                ids.add(participante.getConta().getRegistroId());
            }

            String input = "{\"registration_ids\" : [\"" + conversor.converteJSON(ids) + "\"],\"data\" : {\"message\": \"" + builder.toString() + "\"},}";
            send(input);

        } catch (MalformedURLException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendNotificacaoEventoCancelado(Evento evento) {

        try {
            ConversorJSON conversor = new ConversorJSON(String.class);

            StringBuilder builder = new StringBuilder();

            builder.append( "O evento " );
            builder.append( evento.getDescricao() );
            builder.append(" acaba de ser cancelado.");
         
            List<String> ids = new ArrayList<>();
            
            ids.add(evento.getCriador().getConta().getRegistroId());
            for (Participante participante : evento.getParticipantes()) {
                ids.add(participante.getConta().getRegistroId());
            }

            String input = "{\"registration_ids\" : [\"" + conversor.converteJSON(ids) + "\"],\"data\" : {\"message\": \"" + builder.toString() + "\"},}";
            send(input);

        } catch (MalformedURLException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void send(String msg) throws MalformedURLException, IOException {

        URL url = new URL("https://android.googleapis.com/gcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + API_KEY);

        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();

        os.write(msg.getBytes());
        os.flush();

    }

}
