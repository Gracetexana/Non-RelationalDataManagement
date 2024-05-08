<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Menu</title>
                <style>
                    h1 {color: #FFFFFF}
                    h2 {color: #cccccc}
                    h3 {color: #a6a6a6}
                    body {background-color: #000000}
                </style>
            </head>
            <body>
                <center><table>
                    <thead>
                        <tr>
                            <h1>Menu</h1>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:apply-templates select="menu/item"/>
                    </tbody>
                </table></center>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="item">
        <tr>
            <td>
                <h2><xsl:value-of select="name/text()"/></h2>
            </td>
            <td></td>
            <td>
                <h3>..... $<xsl:value-of select="price/text()"/></h3>
            </td>
        </tr>
    </xsl:template>
</xsl:stylesheet>