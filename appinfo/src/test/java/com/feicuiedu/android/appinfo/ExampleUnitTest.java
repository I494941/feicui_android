package com.feicuiedu.android.appinfo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test1() {

        String s = "model name\t: Intel(R) Core(TM) i5-5200U CPU @ 2.20GHz:stepping\t: 4:microcode\t: 0x19:cpu MHz\t\t: 2194.933:cache size\t: 3072 KB:physical id\t: 0:siblings\t: 4:core id\t\t: 0:cpu cores\t: 4:apicid\t\t: 0:initial apicid\t: 0:fpu\t\t: yes:fpu_exception\t: yes:cpuid level\t: 20:wp\t\t: yes:flags\t\t: fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush mmx fxsr sse sse2 ht syscall nx rdtscp lm constant_tsc rep_good nopl xtopology nonstop_tsc pni pclmulqdq ssse3 cx16 sse4_1 sse4_2 movbe popcnt aes xsave avx rdrand lahf_lm abm 3dnowprefetch rdseed:bogomips\t: 4389.86:clflush size\t: 64:cache_alignment\t: 64:address sizes\t: 39 bits physical, 48 bits virtual:power management:::processor\t: 1:vendor_id\t: GenuineIntel:cpu family\t: 6:model\t\t: 61:model name\t: Intel(R) Core(TM) i5-5200U CPU @ 2.20GHz:stepping\t: 4:microcode\t: 0x19:cpu MHz\t\t: 2194.933:cache size\t: 3072 KB:physical id\t: 0:siblings\t: 4:core id\t\t: 1:cpu cores\t: 4:apicid\t\t: 1:initial apicid\t: 1:fpu\t\t: yes:fpu_exception\t: yes:cpuid level\t: 20:wp\t\t: yes:flags\t\t: fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush mmx fxsr sse sse2 ht syscall nx rdtscp lm constant_tsc rep_good nopl xtopology nonstop_tsc pni pclmulqdq ssse3 cx16 sse4_1 sse4_2 movbe popcnt aes xsave avx rdrand lahf_lm abm 3dnowprefetch rdseed:bogomips\t: 4389.86:clflush size\t: 64:cache_alignment\t: 64:address sizes\t: 39 bits physical, 48 bits virtual:power management:::processor\t: 2:vendor_id\t: GenuineIntel:cpu family\t: 6:model\t\t: 61:model name\t: Intel(R) Core(TM) i5-5200U CPU @ 2.20GHz:stepping\t: 4:microcode\t: 0x19:cpu MHz\t\t: 2194.933:cache size\t: 3072 KB:physical id\t: 0:siblings\t: 4:core id\t\t: 2:cpu cores\t: 4:apicid\t\t: 2:initial apicid\t: 2:fpu\t\t: yes:fpu_exception\t: yes:cpuid level\t: 20:wp\t\t: yes:flags\t\t: fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush mmx fxsr sse sse2 ht syscall nx rdtscp lm constant_tsc rep_good nopl xtopology nonstop_tsc pni pclmulqdq ssse3 cx16 sse4_1 sse4_2 movbe popcnt aes xsave avx rdrand lahf_lm abm 3dnowprefetch rdseed:bogomips\t: 4389.86:clflush size\t: 64:cache_alignment\t: 64:address sizes\t: 39 bits physical, 48 bits virtual:power management:::processor\t: 3:vendor_id\t: GenuineIntel:cpu family\t: 6:model\t\t: 61:model name\t: Intel(R) Core(TM) i5-5200U CPU @ 2.20GHz:stepping\t: 4:microcode\t: 0x19:cpu MHz\t\t: 2194.933:cache size\t: 3072 KB:physical id\t: 0:siblings\t: 4:core id\t\t: 3:cpu cores\t: 4:apicid\t\t: 3:initial apicid\t: 3:fpu\t\t: yes:fpu_exception\t: yes:cpuid level\t: 20:wp\t\t: yes:flags\t\t: fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush mmx fxsr sse sse2 ht syscall nx rdtscp lm constant_tsc rep_good nopl xtopology nonstop_tsc pni pclmulqdq ssse3 cx16 sse4_1 sse4_2 movbe popcnt aes xsave avx rdrand lahf_lm abm 3dnowprefetch rdseed:bogomips\t: 4389.86:clflush size\t: 64:cache_alignment\t: 64:address sizes\t: 39 bits physical, 48 bits virtual:power management:::";

        String[] ss = s.split(":");

        System.out.println(ss[1]);

    }
}