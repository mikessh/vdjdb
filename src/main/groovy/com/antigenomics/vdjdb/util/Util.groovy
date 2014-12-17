/*
 Copyright 2014 Mikhail Shugay (mikhail.shugay@gmail.com)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */


package com.antigenomics.vdjdb.util

import com.milaboratory.core.sequence.AminoAcidSequence

class Util {
    public static InputStreamReader resourceStreamReader(String resourceName) {
        new InputStreamReader(Util.class.classLoader.getResourceAsStream(resourceName))
    }

    public static AminoAcidSequence convert(String aaSeq) {
        if (aaSeq =~ /^[FLSYCWPHQRIMTNKVADEG]+$/)
            return new AminoAcidSequence(aaSeq)
        throw new Exception("Illegal character in amino acid sequence string $aaSeq")
    }

    /**
     * Bring V/D/J alleles to a unified look
     * todo: allow several v segments
     */
    public static List<String> extractVDJ(List<String> vdj) {
        vdj.collect {
            def major = it.split(",")[0]
            major = major.split("\\*")[0] // trim allele if present
            major = major.replaceAll("\"", "")  // zap characters introduced by opening file in Excel
            major.length() > 0 ? major : "."
        }
    }
}
