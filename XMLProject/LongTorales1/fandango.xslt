<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html>
<head>
    <title>fandango</title>
    <style>
        h1 {color: #5A7D9A}
        h2 {color: #FFFFFF}
        h3 {color: #FFFFFF}
        h4 {color: #FFFFFF}
        h5 {color: #FFFFFF}
        body {background-color: #000000}
        img {height: 150; padding: 10 px}
    </style>
</head>
<body>
    <h1>Fandango</h1>
    <hr/>
    <xsl:for-each select="fandango/theater">
        <h2>
            <xsl:value-of select="name/text()"/>
        </h2>
        <table>
        <tr>
            <td>
            <h5>
                <xsl:value-of select="address/number/text()"/>
            </h5>
            </td>
            <td>
            <h5>
                <xsl:value-of select="address/street/text()"/>,
            </h5>
            </td>
            <td>
            <h5>
                <xsl:value-of select="address/city/text()"/>,
            </h5>
            </td>
            <td>
            <h5>
                <xsl:value-of select="address/state/text()"/>
            </h5>
            </td>
            <td>
            <h5>
                <xsl:value-of select="address/zipcode/text()"/>
            </h5>
            </td>
        </tr>
        </table>
        <xsl:for-each select="movie">
            <h3>
                <xsl:value-of select="title"/>
            </h3>
            <h4>
                    Maturity Rating: <xsl:value-of select="maturityRating/text()"/> | Length: <xsl:value-of select="length/text()"/>
            </h4>
            <table>
                <tr>
                    <td>
                        <img>
                        <xsl:attribute name="src">
                        <xsl:value-of select="image"/>
                        </xsl:attribute>
                        </img>
                    </td>
                    <td>
                    </td>
                    <td>
                    <h4>
                        | <xsl:for-each select="showtime">
                            <xsl:value-of select="text()"/> |
                        </xsl:for-each>
                    </h4>
                    </td>
                </tr>
            </table>
            <table>
            <tr>
            <td><h5>| </h5></td>
            <xsl:for-each select="genre">
                <td>
                <h5>
                <xsl:value-of select="text()"/> |
                </h5>
                </td>
            </xsl:for-each>
            </tr>
            </table>
        </xsl:for-each>
        <hr/>
    </xsl:for-each>
</body>
</html>
</xsl:template>
</xsl:stylesheet>