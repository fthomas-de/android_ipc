package android.support.v4.text;


class BidiFormatter$DirectionalityEstimator
{
    private static final byte[] DIR_TYPE_CACHE;
    private static final int DIR_TYPE_CACHE_SIZE = 1792;
    private int charIndex;
    private final boolean isHtml;
    private char lastChar;
    private final int length;
    private final String text;

    static
    {

        int i1;
        DIR_TYPE_CACHE = new byte[1792];
        i1 = 0;

        while (i1 < 1792)
        {
            DIR_TYPE_CACHE[i1] = Character.getDirectionality(i1);
            i1 = i1 + 1;
        }
    }

    BidiFormatter$DirectionalityEstimator(String  r1, boolean  z0)
    {


        this.<init>();
        text = r1;
        isHtml = z0;
        length = r1.length();
    }

    byte dirTypeBackward()
    {

        int i1;
        byte b2;
        lastChar = text.charAt(charIndex + -1);

        if (Character.isLowSurrogate(lastChar) == false)
        {
            charIndex = charIndex + -1;
            b2 = BidiFormatter$DirectionalityEstimator.getCachedDirectionality(lastChar);

            if (isHtml != false)
            {
                if (lastChar != '>')
                {
                    if (lastChar == ';')
                    {
                        b2 = this.skipEntityBackward();
                    }
                }
                else
                {
                    b2 = this.skipTagBackward();
                }
            }
        }
        else
        {
            i1 = Character.codePointBefore(text, charIndex);
            charIndex = charIndex - Character.charCount(i1);
            b2 = Character.getDirectionality(i1);
        }

        return b2;
    }

    byte dirTypeForward()
    {

        int i1;
        byte b2;
        lastChar = text.charAt(charIndex);

        if (Character.isHighSurrogate(lastChar) == false)
        {
            charIndex = charIndex + 1;
            b2 = BidiFormatter$DirectionalityEstimator.getCachedDirectionality(lastChar);

            if (isHtml != false)
            {
                if (lastChar != '<')
                {
                    if (lastChar == '&')
                    {
                        b2 = this.skipEntityForward();
                    }
                }
                else
                {
                    b2 = this.skipTagForward();
                }
            }
        }
        else
        {
            i1 = Character.codePointAt(text, charIndex);
            charIndex = charIndex + Character.charCount(i1);
            b2 = Character.getDirectionality(i1);
        }

        return b2;
    }

    private static byte getCachedDirectionality(char  c0)
    {

        byte b2;
        if (c0 >= '\u0700')
        {
            b2 = Character.getDirectionality(c0);
        }
        else
        {
            b2 = DIR_TYPE_CACHE[c0];
        }

        return b2;
    }

    int getEntryDir()
    {

        int i3, i5;
        byte b4;
        charIndex = 0;
        i3 = 0;
        b4 = (byte) (byte) 0;
        i5 = 0;

        label_3:
        {
            label_2:
            {
                label_0:
                while (charIndex < length)
                {
                    if (i5 != 0)
                    {
                        break label_2;
                    }
                    else
                    {
                        switch (this.dirTypeForward())
                        {
                            case 0:
                                if (i3 != 0)
                                {
                                    i5 = i3;
                                    continue label_0;
                                }
                                else
                                {
                                    b4 = (byte) (byte) -1;
                                    break label_3;
                                }

                            case 1:
                            case 2:
                                if (i3 != 0)
                                {
                                    i5 = i3;
                                    continue label_0;
                                }
                                else
                                {
                                    b4 = (byte) (byte) 1;
                                    break label_3;
                                }

                            case 9:
                                continue label_0;

                            case 14:
                            case 15:
                                i3 = i3 + 1;
                                b4 = (byte) (byte) -1;
                                continue label_0;

                            case 16:
                            case 17:
                                i3 = i3 + 1;
                                b4 = (byte) (byte) 1;
                                continue label_0;

                            case 18:
                                i3 = i3 + -1;
                                b4 = (byte) (byte) 0;
                                continue label_0;

                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            default:
                                i5 = i3;
                                continue label_0;
                        }
                    }
                }
            } //end label_2:


            if (i5 != 0)
            {
                if (b4 == (byte) 0)
                {
                    label_1:
                    while (charIndex > 0)
                    {
                        switch (this.dirTypeBackward())
                        {
                            case 14:
                            case 15:
                                if (i5 != i3)
                                {
                                    i3 = i3 + -1;
                                    continue label_1;
                                }
                                else
                                {
                                    b4 = (byte) (byte) -1;
                                    break label_3;
                                }

                            case 16:
                            case 17:
                                if (i5 != i3)
                                {
                                    i3 = i3 + -1;
                                    continue label_1;
                                }
                                else
                                {
                                    b4 = (byte) (byte) 1;
                                    break label_3;
                                }

                            case 18:
                                i3 = i3 + 1;
                                continue label_1;

                            default:
                                continue label_1;
                        }
                    }

                    b4 = (byte) (byte) 0;
                }
            }
            else
            {
                b4 = (byte) (byte) 0;
            }
        } //end label_3:


        return b4;
    }

    int getExitDir()
    {

        byte b1;
        int i3, i4;
        b1 = (byte) (byte) -1;
        charIndex = length;
        i3 = 0;
        i4 = 0;

        label_5:
        {
            label_4:
            while (charIndex > 0)
            {
                switch (this.dirTypeBackward())
                {
                    case 0:
                        if (i3 != 0)
                        {
                            if (i4 != 0)
                            {
                                continue label_4;
                            }
                            else
                            {
                                i4 = i3;
                                continue label_4;
                            }
                        }
                        else
                        {
                            break label_5;
                        }

                    case 1:
                    case 2:
                        if (i3 != 0)
                        {
                            if (i4 != 0)
                            {
                                continue label_4;
                            }
                            else
                            {
                                i4 = i3;
                                continue label_4;
                            }
                        }
                        else
                        {
                            b1 = (byte) (byte) 1;
                            break label_5;
                        }

                    case 9:
                        continue label_4;

                    case 14:
                    case 15:
                        if (i4 == i3)
                        {
                            break label_5;
                        }
                        else
                        {
                            i3 = i3 + -1;
                            continue label_4;
                        }

                    case 16:
                    case 17:
                        if (i4 != i3)
                        {
                            i3 = i3 + -1;
                            continue label_4;
                        }
                        else
                        {
                            b1 = (byte) (byte) 1;
                            break label_5;
                        }

                    case 18:
                        i3 = i3 + 1;
                        continue label_4;

                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    default:
                        if (i4 != 0)
                        {
                            continue label_4;
                        }
                        else
                        {
                            i4 = i3;
                            continue label_4;
                        }
                }
            }

            b1 = (byte) (byte) 0;
        } //end label_5:


        return b1;
    }

    private byte skipEntityBackward()
    {

        int i1, i4;
        byte b8;
        i1 = charIndex;

        label_7:
        {
            label_6:
            {
                while (charIndex > 0)
                {
                    i4 = charIndex + -1;
                    charIndex = i4;
                    lastChar = text.charAt(i4);

                    if (lastChar != '&')
                    {
                        if (lastChar == ';')
                        {
                            break label_6;
                        }
                    }
                    else
                    {
                        b8 = (byte) (byte) 12;
                        break label_7;
                    }
                }
            } //end label_6:


            charIndex = i1;
            lastChar = ';';
            b8 = (byte) (byte) 13;
        } //end label_7:


        return b8;
    }

    private byte skipEntityForward()
    {

        int i3;
        char c4;
        label_8:
        {
            while (charIndex < length)
            {
                i3 = charIndex;
                charIndex = i3 + 1;
                c4 = text.charAt(i3);
                lastChar = c4;

                if (c4 == ';')
                {
                    break label_8;
                }
            }
        } //end label_8:


        return (byte) 12;
    }

    private byte skipTagBackward()
    {

        int i1, i5, i17;
        char c4, c18;
        byte b9;
        i1 = charIndex;

        label_11:
        {
            label_10:
            {
                label_9:
                while (charIndex > 0)
                {
                    i5 = charIndex + -1;
                    charIndex = i5;
                    lastChar = text.charAt(i5);

                    if (lastChar != '<')
                    {
                        if (lastChar != '>')
                        {
                            if (lastChar != '\"')
                            {
                                if (lastChar != '\'')
                                {
                                    continue label_9;
                                }
                            }

                            c4 = lastChar;

                            if (charIndex > 0)
                            {
                                i17 = charIndex + -1;
                                charIndex = i17;
                                c18 = text.charAt(i17);
                                lastChar = c18;

                                if (c18 != c4)
                                {
                                }
                            }
                        }
                        else
                        {
                            break label_10;
                        }
                    }
                    else
                    {
                        b9 = (byte) (byte) 12;
                        break label_11;
                    }
                }
            } //end label_10:


            charIndex = i1;
            lastChar = '>';
            b9 = (byte) (byte) 13;
        } //end label_11:


        return b9;
    }

    private byte skipTagForward()
    {

        int i0, i5, i16;
        char c4, c18;
        byte b9;
        i0 = charIndex;

        label_13:
        {
            label_12:
            while (charIndex < length)
            {
                i5 = charIndex;
                charIndex = i5 + 1;
                lastChar = text.charAt(i5);

                if (lastChar != '>')
                {
                    if (lastChar != '\"')
                    {
                        if (lastChar != '\'')
                        {
                            continue label_12;
                        }
                    }

                    c4 = lastChar;

                    if (charIndex < length)
                    {
                        i16 = charIndex;
                        charIndex = i16 + 1;
                        c18 = text.charAt(i16);
                        lastChar = c18;

                        if (c18 != c4)
                        {
                        }
                    }
                }
                else
                {
                    b9 = (byte) (byte) 12;
                    break label_13;
                }
            }

            charIndex = i0;
            lastChar = '<';
            b9 = (byte) (byte) 13;
        } //end label_13:


        return b9;
    }
}
