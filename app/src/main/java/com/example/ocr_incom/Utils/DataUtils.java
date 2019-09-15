package com.example.ocr_incom.Utils;

import android.util.Log;

import com.example.ocr_incom.Data.DataTemplate;

import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    private List<String> imageText = new ArrayList<>();
    private List<String> scheme = new ArrayList<>();
    private List<DataTemplate> templateResult = new ArrayList<>();

    //Filters
    private String[] filterTemplateArrayText = {"TFFR", "KryT", "TA3", "VN"};

    public DataUtils() {

    }

    /**
     * Breaking image text
     *
     * @param text get image text
     */
    public void breakingImageText(String text) {
        String[] _breaking = text.split("\\n");
        for (String breakingText : _breaking) {
            replaceImageText(breakingText);
        }
    }

    /**
     * Replace breaking Image Text
     *
     * @param text breaking text
     */
    private void replaceImageText(String text) {
        String[] temp = text.split("\\s");
        int _length = text.split("\\s").length;
        String[] _replace = new String[_length];

        for (int i = 0; i < _length; i++) {
            _replace[i] = replaceTemplateName(temp[i]);
        }

        Log.println(Log.INFO, "Plan", replaceFilterChar(newNameTemplate(_replace)));
        imageText.add(replaceFilterChar(newNameTemplate(_replace)));
    }


    private String newNameTemplate(String[] _replace) {
        int _lengthText = _replace.length;

        switch (_lengthText) {
            case 2: {
                return _replace[0] + _replace[1];
            }
            case 3: {
                return _replace[0] + _replace[1] + _replace[2];
            }
        }

        return _replace[0];
    }

    /**
     * Replace array text Template
     *
     * @param templateText get array text
     */
    private String replaceTemplateName(String templateText) {
        for (String s : filterTemplateArrayText) {
            if (templateText.equals(s)) {
                switch (s) {
                    case "KryT": {
                        return "Жгут ";
                    }
                    case "TA3": {
                        return "ГАЗ ";
                    }
                    case "VN": {
                        return "VW ";
                    }
                    default: {

                    }
                }
            }
        }
        return templateText + " ";
    }

    /**
     * Replace char is get text
     *
     * @param replaceCharText replace text
     */
    private String replaceFilterChar(String replaceCharText) {
        String _temp = replaceCharText;
        if (replaceCharText.endsWith(" ")) {
            _temp = replaceCharText.substring(0, replaceCharText.length() - 1);
            _temp = _temp.replace("|", "");
            if (_temp.endsWith("r")) {
                return _temp.replace("r", "Г");
            } else if (_temp.endsWith("94")) {
                return _temp.substring(0, replaceCharText.length() - 4);
            } else if (_temp.startsWith("VSN")) {
                return _temp.replace("O", "0");
            } else if (_temp.endsWith("5")) {
                return Utils.replaceStringIsChar(_temp, _temp.length() - 1, 'Б');
            }
        }
        return _temp.replace(",", ".");
    }

    /**
     * Search plank and scheme
     *
     * @param dataTemplate get Data base all Template
     */
    public void searchAllTemplateInScheme(List<DataTemplate> dataTemplate) {
        for (int i = 0; i < imageText.size(); i++) {
            findAllTemplate(dataTemplate, i);
        }
        for (int i = 0; i < imageText.size(); i++) {
            findAllScheme(i);
        }
    }

    /**
     * Search template is image text
     *
     * @param dataTemplates get Data base all Template
     * @param pos           get pos array text
     */
    private void findAllTemplate(List<DataTemplate> dataTemplates, int pos) {
        for (int i = 0; i < dataTemplates.size(); i++) {
            if (imageText.get(pos).contains(dataTemplates.get(i).getPlank())) {
                DataTemplate _dataTemplate = new DataTemplate();
                _dataTemplate.setCount(dataTemplates.get(i).getCount());
                _dataTemplate.setPlank(dataTemplates.get(i).getPlank());
                _dataTemplate.setTemplate(dataTemplates.get(i).getTemplate());
                templateResult.add(_dataTemplate);
                break;
            }
        }
    }

    /**
     * Search scheme is template data
     *
     * @param pos get pos array data
     */
    private void findAllScheme(int pos) {
        if (isNoPlank(pos)) {
            scheme.add(imageText.get(pos));
        }
    }

    /**
     * If all get dataTemplate is plank
     *
     * @param pos get pos is templateResult
     */
    private boolean isNoPlank(int pos) {
        for (int i = 0; i < templateResult.size(); i++) {
            if (imageText.get(pos).equals(templateResult.get(i).getPlank())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get one template is all base Template
     *
     * @param allTemplate find Template
     * @param plank       get text find template
     */
    public DataTemplate getOneTemplate(List<DataTemplate> allTemplate, String plank) {
        for (DataTemplate oneTemplate : allTemplate) {
            if (oneTemplate.getPlank().equals(plank)) {
                return oneTemplate;
            }
        }
        return null;
    }


    public List<DataTemplate> getTemplate() {
        return templateResult;
    }

    public List<String> getScheme() {
        return scheme;
    }

}
