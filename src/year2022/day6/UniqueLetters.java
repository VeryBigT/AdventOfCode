package year2022.day6;

import java.util.HashSet;

public class UniqueLetters {
    static String testStr1 = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";
    static String testStr2 = "qvllndllhzhfzhhdzhddhjdjggvnvhvccmffwllqgqmmfjfqfhhtrrzczjczzlplddfpptqqfbqffmnmjnnqppfjfccgnnmqqsvvdbbgppjvpjvpjjctjjttwtrrdldlcddrvddqndqnqwqwzwfwwzczggcppgzpzhpzhppprfffbhhwmhhtftstrsrvsrvsrvvshvssnwwpllhfhnnfflcltlblzlqlvqlvlcldcccpptggtdgdjdbbrggmbmnncscbssqrrjddvcvgvfflpppgpvphvphhpcpzpzvvctvctvthtwtfwwbrrhhlplmlwwlqlnlhhtmhmmqlqplllrvrgvrvrffzfgfjfjtjmjvmjmwmvvjffmpfphfhvfvmfvmmhpphhltthgttgccqggpzpfpqpcpvcpvcvvqtqvqbbrlrtllmrmllhmhvmhhvzhvzvrrrzjzbbtvvbgvbbfnnqndqnnpnbnbnlnggwqggmgmqmgmbbmccgqcqbccpvcvnnhvvrvlrrcwrcwrcwrwbrwwzbwbdbfddpttntzzjszsnznbndnzngzgccjrcjchcffmlmqqlrqqzsqzzsbsnsttzpztpzpggzrrttbqqplpqlqjjqcqvccdzdccthccvfcvvqvhqhfhhzwzpzwppgpttntssflfjjrwrqrjrppptlltptpvttpfpwpswpppzzsrzssqllbnlljpllrjllsrlrhrdrmdrmrrpsrprnrffgrffdqdhdqhhrhggwqqlddsbsqbqtqdtdhdvhhbdhdzhdhhtrrppzddgfgzgpzpvpfpnpptggltggbnbppqffzfrzzzsbsrrdgrddwsdsqddhpdpbpvpfvppfsfgfngffzmzbzlblclsccvqvqmmjtjqtjjlcjllsddjqddhldlvlrrbgbrgbrrdzzpfpggqnqbqrrqbbgjgppqgpgwgqqndncndnpdnnbvbnvnwnjjgppzlplqqdgqghqgqzggjssqmmwwcfcpptrpprggrppgbplmzwmdtnpqwzcrthqbppwbgcvgqrpfpnbscnhvrllpvpqwnsslcjrqtvdccprvqfrpswtpvzdzlgtmmvppdmhgdbbsmrbqpqspdhpqgfjznqzphrnggcbzhdqrgvzcfzrhtrlssgmjjghqsjtghhnwjffqrrfslfnsvvdvfjqbfpffrrstdhggvbfwtfpfgswqlfdrnjpjmwzptlbmwgghgwqrphcrvfmhrplllgbnjlprllmjwccphsflntgpnbmdbfqcdsbgvrnfznfrlcfvswqfrqvdnbjsflnsmlcrdstzppmcvbgdtcvgztbdzqbwhmwcfvbwjjcdgbnwjwzrrdqhpgscwtnztjsfstzfwftcldjgvdvwbzrlbdslwttbqpnlwbjcjwqgtrgcglsgtdqbqbnqznptzzbwffwlwzvvtdpcjbvhnswzptclpbndcdvsfmcrmwwgzdfsszqjjdztmtsqgfqzjpctfdpwnzbpnzzwngqnghntblndfrnjzdrmgbqmzbdqfzctrgshwqgfgqssqjltrqlzjswjhmpgwwjdwcjpnsvgrvbfpmlmmwzmbdjwsrjthppfrccjgnmwlvqlprgslbwtbbzlqbznczmsmhsfdcqnwblprcpbzzwfllbnldvpjcwsdhglrzjsptmsjdjqzsmgvhjfjrrtvvbjlmzjsntnrggwbpjlrjggfgqzvswtggthzfmfjnmrzrttbzqpwpsnmdtnbfblpfgslgcmjlbdpshnnrbhvwsbrnvdmjqhvhdjhbfzjmqrmqmdthhzvnrmqcnbtwcdjdqfvdgvmfbhrfqnmdncrddggtcppjlznbsnntppjtnsqsrjwvfrzpnzqcrzhhdflfmmtmwcvtpzbqhdwsczffcqhtdbdjblmgnrmhlqcsvcpgghhvwqhdtzpzlpfllchzltqgcwgfqnbzhgzmdwqdlwnvhqmpqjqnjbhjctslghdqvctdmjfwdfpdjnhdndzwsfjzlmsbmfmzvnvpqgqhtngvgqmlrrzsfmwlcwsscvghjvrzjjqbnplnjzqswpblwzwczhwbhhnjmctnmwlbqqfmnlwdcrptlmfjpjrnpcvmhffjhwhmntdzpdjzwzhrrsdvmjlwdtcpvjfmfzfsrgjghhlvmjjjczgmhvrfpgqbnhldwbrjgzmnszzbssfzcggrwmdfvddwsdmnwtwfwlfnwlvzlctfblbtrjvcwjjdljplcrjhwqslppwwtvfqwsjlfmdznmcdzdmgvmmsrfcclcvhtrhlsjzrbjwrjlfnvqhqvmpzmdttnbhfcvnqlrqbcsvtvwfccjstjpmhqgwlnrzjjmfdszflmglrdbpqhqhqsdfzrcljbdvvnlcqfllmnqcjfzjppdsjwshfschzqbnwfqnpwhqnmwsjbtcgvrljsrtzvcvghcjjlqsngglcggqpntrrhbjpbfhmvpltmnfmfdtwnczwfbvjcqnhvppjftwvwsrlhvvcjtsfptpqgrmrqwwddnqmnmfgrlnphbpqhhhvglqgtwvnwvnbssftmwttmfrffwtzhrpqspclvgchwqwcsgwqwwvpgcwngrcfmhbhflwfbfchlphdzdcrflfmfclsngtlwrqcrsgrdzcpdsvvcdbhgtljmbntbbcqgjqfsbfwzlfsnljpjdcnmjlqrwpmlvwgdlrrdgfhdqhzgltmclzgzzhmrbggsmgtpqdrgmjtlzwstrwbpvhppvsmdqvvwwglzjgdswjszqmrdbmshbhhcstpcsjdbvgjnvcmvhbtclrlmlgnvppgvncsrfchdbqjrclwwlnchmcgvshfsbsvvcvjrsgjlnsfqtqmgntffwnqjtldcqbcqhsgztllstswwqnfrswpchqhnfzzzszqjztzfrgrbjdbjlpvqfqrlrmmpbfbbcclrgmnlzwqrjhqrstswjpgsrtnlwsbqthzpvdzllzqmdmbvvtcztftvlwphhjzbfnrvccfmhmvmzlbrzlnppfzcsffjvjmbgpvlwgwszpztjpsrbnftqtdrbnljtbrjzzbwlsvtwtlwptdtnmtncvcblcmdngjzmctlqtzchncccnwjzrrmmmnllbhrnhwtqjsnvcslrqjfbfndqvdlrjshdzmlprtzbtnhthdqhplwzdbnjmgzlzrbzrvrqnflwfmsmbssqnbcddnvdpltpmplpdzvtjrslcdcnrdplwtjtvctwfzhlvwwqqtbqcjjwhhnpmvgzhqmqfgthwbphrmrtdghchsmwghdqjgjgmpddbrtngtvhqgjfrplrdgpbnhqvswrmqhcmsqvsqmqsgwjndwjrbrhvrctmmrmfwpsgfgdlrzpslpflgvwrgcthgcrnhgrzsmqdgdssjgspfhmqfmjfpmwqhnfjdvqzhpndvnbmqglbrjmdrwgmgctrgzpsdvfbmcstcslblmvnprphntgslmlrqwthrndrhtbccgzzfsglhgqztcsnqjwfzbzlvrpbvswbhrwdsrhrrpnrmsbvbvjccbdsdcfrrzpgwjtnnnvjwlcppwzdqsbdzpfjplrlfgvjpsmbzwpwlghnvqgddfjvrsztrpzlfgmqqzrfcgglghndbhgbmldglclhldljjdslvhzshshtqwhqnbzhvqrcmwdmcmhjcrmdmhrwnwcbhvbbrwrbtfdnztwnbpdfjfhgrmcpngftsvbsmsptnwcvvllnmbnsntbzmwnhfdptbtzswtjzdqwjdhprnjwvhzpscjvlsgrhdrmmrmhzhwwtslzdjqmzfncnmgplhnmwrvqhslvchtjcmpzpjpnpfbjptvvwcsmhgdjtsqrjlfpnfdncpqqmpgpvtlvwljlsqbnhtsqgfwlsmdjpgtvgjvjcrnnzmbllqzlrfdnlffgmtphhhgbcjgdlpzqpwmjwtcmdrsmtnmddftwczbsddtppsptbwfvpnfnsqmsgcfqfmnzffzqgcdvwzrgdwhmnzmrlhcdpdsltnsmjzdqwmmpwvjqbbwsrfgzh";
    static String testStr3 = "bvwbjplbgvbhsrlpgdmjqwftvncz";
    static String testStr4 = "nppdvjthqldpwncqszvftbrmjlhg";
    static String testStr5 = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
    static String testStr6 = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";

    static int a = 1 << 'a' % 32;
    static int b = 1 << 'b' % 32;
    static int c = 1 << 'c' % 32;
    static int d = 1 << 'd' % 32;
    static int e = 1 << 'e' % 32;
    static int f = 1 << 'f' % 32;
    static int g = 1 << 'g' % 32;
    static int h = 1 << 'h' % 32;
    static int i = 1 << 'i' % 32;
    static int j = 1 << 'j' % 32;
    static int k = 1 << 'k' % 32;
    static int l = 1 << 'l' % 32;
    static int m = 1 << 'm' % 32;
    static int n = 1 << 'n' % 32;
    static int o = 1 << 'o' % 32;
    static int p = 1 << 'p' % 32;
    static int q = 1 << 'q' % 32;
    static int r = 1 << 'r' % 32;
    static int s = 1 << 's' % 32;
    static int t = 1 << 't' % 32;
    static int u = 1 << 'u' % 32;
    static int v = 1 << 'v' % 32;
    static int w = 1 << 'w' % 32;
    static int x = 1 << 'x' % 32;
    static int y = 1 << 'y' % 32;
    static int z = 1 << 'z' % 32;

    static int wantedLetters = 14;

    public static int f1(String str) {
        char[] charArr = str.toCharArray();
        int start = 0;
        int end = wantedLetters;
        int ul;
        while(end < str.length()) {
            ul = uniqueLetters1(charArr, start, end);
            if (ul == wantedLetters)
                return end;
            start += wantedLetters - ul;
            end += wantedLetters - ul;
        }
        return -1;
    }

    public static int f2(String str) {
        char[] charArr = str.toCharArray();
        int start = 0;
        int end = wantedLetters;
        int ul;
        while(end < str.length()) {
            ul = uniqueLetters2(charArr, start, end);
            if (ul == wantedLetters)
                return end;
            start += wantedLetters - ul;
            end += wantedLetters - ul;
        }
        return -1;
    }

    private static int uniqueLetters1(char[] charArr, int start, int end) {
        HashSet<Character> set = new HashSet<>();
        for(int i = start; i < end; i++) {
            set.add(charArr[i]);
        }
        return set.size();
    }

    private static int uniqueLetters2(char[] charArr, int start, int end) {
        int mask = 0;
        for(int i = start; i < end; i++) {
            mask |= charToIntMask(charArr[i]);
        }
        return numberOfOnes(mask);
    }

    private static int numberOfOnes(int mask) {
        int result = 0;
        while (mask > 0) {
            result += mask % 2;
            mask >>= 1;
        }
        return result;
    }

    private static int charToIntMask(char ch) {
        return switch (ch) {
            case 'a' -> a;
            case 'b' -> b;
            case 'c' -> c;
            case 'd' -> d;
            case 'e' -> e;
            case 'f' -> f;
            case 'g' -> g;
            case 'h' -> h;
            case 'i' -> i;
            case 'j' -> j;
            case 'k' -> k;
            case 'l' -> l;
            case 'm' -> m;
            case 'n' -> n;
            case 'o' -> o;
            case 'p' -> p;
            case 'q' -> q;
            case 'r' -> r;
            case 's' -> s;
            case 't' -> t;
            case 'u' -> u;
            case 'v' -> v;
            case 'w' -> w;
            case 'x' -> x;
            case 'y' -> y;
            case 'z' -> z;
            default -> -1;
        };
    }

    public static void main(String[] args) {
        long start, end;
        start = System.currentTimeMillis();
        for(int i = 0; i < 1000; ++i) {
            f1(testStr1);
            f1(testStr2);
            f1(testStr3);
            f1(testStr4);
            f1(testStr5);
            f1(testStr6);
        }
        end = System.currentTimeMillis();
        System.out.println("Laufzeit f1: " + (end - start));

        start = System.currentTimeMillis();
        for(int i = 0; i < 1000; ++i) {
            f2(testStr1);
            f2(testStr2);
            f2(testStr3);
            f2(testStr4);
            f2(testStr5);
            f2(testStr6);
        }
        end = System.currentTimeMillis();
        System.out.println("Laufzeit f2: " + (end - start));
    }
}
