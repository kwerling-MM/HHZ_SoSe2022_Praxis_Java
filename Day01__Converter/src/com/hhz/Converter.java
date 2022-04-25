package com.hhz;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public ArrayList<String> convert(List<String> orgText) {
        ArrayList <String> retVal = new ArrayList<String>();

        addLatexFileStart( retVal );

        for(int i = 0; i < orgText.size(); i ++ ){
            String str = orgText.get(i).trim();

            if( str.startsWith("######") ) {
                retVal.add("\\textbf{"
                + str.substring(6).trim()
                + "}");
            } else if( str.startsWith("#####") ) {
                retVal.add("\\subparagraph{"
                        + str.substring(5).trim()
                        + "}");
            } else if( str.startsWith("####") ) {
                retVal.add("\\paragraph{"
                        + str.substring(4).trim()
                        + "}");
            } else if( str.startsWith("###") ) {
                retVal.add("\\subsubsection{"
                        + str.substring(3).trim()
                        + "}");
            } else if( str.startsWith("##") ) {
                retVal.add("\\subsection{"
                        + str.substring(2).trim()
                        + "}");
            } else if( str.startsWith("#") ) {
                retVal.add("\\section{"
                        + str.substring(1).trim()
                        + "}");
            }

        }

        addLatexFileEnd( retVal );

        return retVal;
    }

    private void addLatexFileStart(ArrayList<String> retVal) {
        retVal.add("\\documentclass[12pt, a4paper ]{article}");
        retVal.add("\\usepackage[utf8]{inputenc}");
        retVal.add("");
        retVal.add("\\begin{document}");
        retVal.add("");
    }

    private void addLatexFileEnd(ArrayList<String> retVal) {
        retVal.add("");
        retVal.add("");
        retVal.add("\\end{document}");
    }
}
