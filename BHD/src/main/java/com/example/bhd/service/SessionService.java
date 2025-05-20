package com.example.bhd.service;

import jakarta.servlet.http.HttpSession;

public interface SessionService {
    public void startSession(HttpSession session, Integer movieId);

    public void addAttribute(HttpSession session, String key, Object value);

    public Object getAttribute(HttpSession session, String key);

    public void removeAttribute(HttpSession session, String key);

    public void clearSession(HttpSession session);

    void removeAllAttributes(HttpSession session);
}
