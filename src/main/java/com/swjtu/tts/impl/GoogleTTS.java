package com.swjtu.tts.impl;

import com.swjtu.lang.Lang;
import com.swjtu.tts.AbstractTTS;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;

public final class GoogleTTS extends AbstractTTS{
    private final static String url = "https://translate.google.cn/translate_tts";

    public GoogleTTS() {
        super(url);
        setLangSupport();
    }

    @Override
    public void setLangSupport() {
        langMap.put(Lang.ZH, "zh-CN");
        langMap.put(Lang.EN, "en");
        langMap.put(Lang.FRA, "fr");
        langMap.put(Lang.RU, "ru");
        langMap.put(Lang.DE, "de");
        langMap.put(Lang.KOR, "ko");
        langMap.put(Lang.JP, "ja");
    }

    @Override
    public void setFormData(Lang source, String text) {
        formData.put("ie", "UTF-8");
        formData.put("q", text);
        formData.put("tl", langMap.get(source));
        formData.put("total", String.valueOf(1));
        formData.put("idx", String.valueOf(0));
        formData.put("textlen", String.valueOf(11));
        formData.put("tk", token(text));
        formData.put("client", "t");
    }

    private String token(String text) {
        String tk = "";
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
        try {
            FileReader reader = new FileReader("./tk/Google.js");
            engine.eval(reader);

            if (engine instanceof Invocable) {
                Invocable invoke = (Invocable)engine;
                tk = String.valueOf(invoke.invokeFunction("token", text));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk;
    }
}
