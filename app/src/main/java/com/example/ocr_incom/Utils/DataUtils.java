package com.example.ocr_incom.Utils;

import com.example.ocr_incom.Data.DataTemplate;

import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    private List<String> imageText = new ArrayList<>();
    private List<String> scheme = new ArrayList<>();
    private List<DataTemplate> templateResult = new ArrayList<>();

    //Filters
    private String[] filterProject = {"A53", "A2", "A11", "A61", "A22", "A124", "A17", "A44", "AS3"};
    private String[] filterErrorText = {"101.10.04.690", "142.10.06.660K", "152.10.81.2005", "S300.10.72.60oГ",
            "152.10.69.1305", "MPY-1.55.490M", "A65R33.3724240-", "101.10.51.5305", "PCM-10O0.15.47.630A", "MPy-1.53.610", "MPy-1.53.540", "33081-37243000"
            , "161.10.01.820r", "161.10.01.80OB", "161.10.01.610A", "KnuMaTMK", "KauMaTWK", "MPy-1.52.480B", "3000.37.40.60OA",
            "S300.10.05.250r", "181.10.72.980A", "181.10.71.980EV", "1181.10.28.130", "1181.10.03.410A", "1161.10.03.930", "1161.10.02.860B", "161.10.02.7206",
            "152.10.51.190A", "1142.10.77.070r", "1142.10.72.620A", "142.10.72.490A", "222700-37240O04", "2375.37 20.000", "TFFR 22011-00.00.00 34", "TFFR 23011-00.00.00 34", "1.10.5305"};


    private String[] successErrorText = {"101.10.04.690Д", "142.10.06.660Ж", "152.10.81.200Б", "S300.10.72.600Г",
            "152.10.69.130Б", "МРУ-1.55.490И", "A65R33.3724240", "101.10.51.530Б", "РСМ-100.15.47.630А", "МРУ-1.53.610", "МРУ-1.53.540", "33081-3724300"
            , "161.10.01.820Г", "161.10.01.800B", "161.10.01.610Д", "Климатик 1.0", "Климатик БП 1.0", "МРУ-1.52.480В", "3000.37.40.600A",
            "S300.10.05.250Г", "181.10.72.980Д", "181.10.71.980E", "181.10.28.130Б", "181.10.03.410A", "161.10.03.930Г", "161.10.02.860B", "161.10.02.720Б", "152.10.51.190Д", "142.10.77.070Г",
            "142.10.72.620A", "142.10.72.490Д", "222700-3724004", "2375.37.20.000", "TFFR 22011-00.00.00", "TFFR 23011-00.00.00", "1.10.530Б"};

    public DataUtils( ) {

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
        String[] _replace = text.split("\\s");
        for (int i = 0; i < _replace.length; ) {
            if (isAddFilterImageText(_replace[0]) && _replace.length > 1) {
                imageText.add(replaceErrorText(replaceArrayText(_replace)));
                break;
            } else if (isAddFilterImageText(_replace[0])) {
                break;
            }
            imageText.add(replaceErrorText(replaceFilterChar(_replace[0])));
            break;
        }
    }

    /**
     * Filter project plank
     *
     * @param text get image Text
     */
    private boolean isAddFilterImageText(String text) {
        for (String _filterProject : filterProject) {
            if (text.equals(_filterProject)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Replace array Text is filter
     *
     * @param arrayText get Array text
     */
    private String replaceArrayText(String[] arrayText) {
        StringBuilder _replaceBuilder = new StringBuilder();
        for (int i = 1; i < arrayText.length; i++) {
            _replaceBuilder.append(replaceFilterChar(arrayText[i])).append(" ");
        }
        String _replace = String.valueOf(_replaceBuilder);
        if (_replace.endsWith(" ")) {
            return String.valueOf(_replace.subSequence(0, _replace.toCharArray().length - 1));
        } else {
            return _replace;
        }
    }

    /**
     * Replace char is get text
     *
     * @param replaceCharText replace text
     */
    private String replaceFilterChar(String replaceCharText) {
        if (replaceCharText.endsWith("r")) {
            return replaceCharText.replace("r", "Г");
        } else if (replaceCharText.endsWith("6") && !replaceCharText.equals("AC.014.1516")) {
            return replaceCharText.replace("6", "Б");
        } else if (replaceCharText.startsWith("|")) {
            return replaceCharText.replace("|", "");
        }
        return replaceCharText.replace(",", ".");
    }


    /**
     * Search plank and scheme
     *
     * @param dataTemplate get Data base all Template
     */
    public void searchTemplateInScheme(List<DataTemplate> dataTemplate) {
        for (int i = 0; i < imageText.size(); i++) {
            findTemplate(dataTemplate, i);
        }
        for (int i = 0; i < imageText.size(); i++) {
            findScheme(i);
        }
    }

    /**
     * Search template is image text
     *
     * @param dataTemplates get Data base all Template
     * @param pos           get pos array text
     */
    private void findTemplate(List<DataTemplate> dataTemplates, int pos) {
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

    private void findScheme(int pos) {
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
        System.out.println("No plank " + imageText.get(pos));
        return true;
    }

    /**
     * Replace error text
     *
     * @param text get image text
     */
    private String replaceErrorText(String text) {
        for (int i = 0; i < filterErrorText.length; i++) {
            if (filterErrorText[i].equals(text)) {
                return successErrorText[i];
            }
        }
        return text;
    }


    public List<DataTemplate> getTemplate( ) {
        return templateResult;
    }

    public List<String> getScheme( ) {
        return scheme;
    }

}
