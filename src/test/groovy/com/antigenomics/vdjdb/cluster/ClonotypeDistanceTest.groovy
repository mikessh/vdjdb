package com.antigenomics.vdjdb.cluster

import com.antigenomics.vdjdb.sequence.SearchScope
import org.junit.Test

import static com.antigenomics.vdjdb.TestUtil.TEST_SAMPLE

class ClonotypeDistanceTest {
    @Test
    void sampleTest() {
        def distanceCalc = new ClonotypeDistanceCalculator(TEST_SAMPLE,
                new SearchScope(1, 0, 1))

        int n = TEST_SAMPLE.findAll { it.coding }.size()
        def distances = distanceCalc.computeDistancesTo(TEST_SAMPLE)
        assert distances.findAll { it.score == 0 && it.from == it.to }.size() == n
        int n0 = distances.findAll { it.score == 0 }.size()
        assert n0 > n
        assert distances.size() > n0
    }
}