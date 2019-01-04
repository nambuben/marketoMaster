#set($activeMemberCode = "A")

#if ( !$uWAAMembership_cList.isEmpty() )


#foreach($member in $uWAAMembership_cList)



#if( $member.membStatusCode.equals($activeMemberCode) )

## Formats our date object in order to display on the card.
#set( $cardDateOptions = {  
  "formats" : {  
    "userin" : "yyyy-MM-dd",  
    "userout" : "MM/d/yy"  
  },  
  "timezones" : {  
    "userin" : "America/Los_Angeles",  
    "userout" : "America/Los_Angeles"  
  },  
  "locale" : $date.getLocale()  
} )  
#set( $expirationDatelike = $member.membNextRenewalDate )  
#set( $expirationDate = $convert.parseDate(  
  $expirationDatelike,  
  $cardDateOptions.formats.userin,  
  $cardDateOptions.locale,  
  $date.getTimeZone().getTimeZone($cardDateOptions.timezones.userin)  
) )  
#set( $formattedCardRenewalDatemember = $date.format(  
  $cardDateOptions.formats.userout,  
  $expirationDate,  
  $cardDateOptions.locale,  
  $date.getTimeZone().getTimeZone($cardDateOptions.timezones.userout)  
) )  



## Formats our date object in order to display in the text on the right.
#set( $paragraphDateOptions = {  
  "formats" : {  
    "userin" : "yyyy-MM-dd",  
    "userout" : "MMMM dd, yyyy"  
  },  
  "timezones" : {  
    "userin" : "America/Los_Angeles",  
    "userout" : "America/Los_Angeles"  
  },  
  "locale" : $date.getLocale()  
} )  
#set( $expirationDatelike = $member.membNextRenewalDate )  
#set( $expirationDate = $convert.parseDate(  
  $expirationDatelike,  
  $paragraphDateOptions.formats.userin,  
  $paragraphDateOptions.locale,  
  $date.getTimeZone().getTimeZone($paragraphDateOptions.timezones.userin)  
) )  
#set( $formattedParagraphRenewalDatemember = $date.format(  
  $paragraphDateOptions.formats.userout,  
  $expirationDate,  
  $paragraphDateOptions.locale,  
  $date.getTimeZone().getTimeZone($paragraphDateOptions.timezones.userout)  
) )  
 

##Does the Life/Annual Check - setting background for the card, and determining which text should appear
#set($membershipType = $member.membTypeCode)

#if( $membershipType.contains("A"))

#set($cardBackgroundURL = "https://explore.uw.edu/rs/131-AQO-225/images/2019_MemberCard_Annual_600x310.jpg")
#elseif( $membershipType.contains("I"))

#set($cardBackgroundURL = "https://explore.uw.edu/rs/131-AQO-225/images/2019_MemberCard_Life_600x310.jpg")


#elseif( $membershipType.contains("L"))

#set($cardBackgroundURL = "https://explore.uw.edu/rs/131-AQO-225/images/2019_MemberCard_Life_600x310.jpg")
#else
Error - did not catch anything
#end



    <table role="Membership Module" width="100%" cellpadding="0" cellspacing="0" style="width:100%;" class="deviceWidth_11f26 mktoModule" mktoname="Membership Information" id="membershipModule"> 
                              <tbody> 
                                <tr> 
                                  <td bgcolor="#efeef4"> 
                                    <table width="100%" role="Inner Container" style="width: 100%; margin: 0 auto; border-collapse: collapse;" align="center" cellpadding="0" cellspacing="0"> 
                                      <tbody> 
                                        <tr> 
                                          <td class="member-card block_11f26 center_11f26 padding_bottom_lee1z full-width_fa11fjf1" width="300" height="188" valign="top" style="background-image: url(${cardBackgroundURL});background-repeat:no-repeat;background-position: center;background-size:cover;color: #000000;font-family: 'Open Sans', Arial, sans-serif;font-size: 16px;line-height: 20px;min-height:205px;"> 
                                            <!--[if gte mso 9]>
                                            <v:rect xmlns:v="urn:schemas-microsoft-com:vml" fill="true" stroke="false" style="width:300px;height:188px;">
                                              <v:fill type="tile" src="${cardBackgroundURL}" color="#efefef" />
                                              <v:textbox inset="0,0,0,0">
                                            <![endif]--> 
                                            <table align="bottom" width="100%" cellpadding="0" cellspacing="0" style="width:100%;"> 
                                              <tbody> 
                                                <tr> 
                                                  <td class="extra-space2" style="line-height: 1px; font-size: 1px;" height="35">&nbsp;</td> 
                                                </tr> 
                                                <tr> 
                                                  <td class="extra-space2" style="line-height: 1px; font-size: 1px;" height="85">&nbsp;</td> 
                                                </tr> 
                                                <tr> 
                                                  <td style="padding-left:10px;text-align: left;"> 
                                                    <div class="mktoText" id="memberNumber" mktoname="Member Number">
                                                      <span style="font-size: 12px;font-family: 'Open Sans', Arial, sans-serif;">${lead.advanceID}</span>
                                                    </div> </td> 
                                                </tr> 
                                                <tr> 
                                                  <td style="padding-left:10px;text-align: left;font-family: 'Open Sans', Arial, sans-serif;font-weight: bold;"> 
                                                    <div class="mktoText" id="memberName" mktoname="Member Name">
                                                      ${lead.FirstName} ${lead.LastName}
                                                    </div> </td> 
                                                </tr>
#if( $membershipType.contains("A"))                 
                                                <tr> 
                                                  <td style="padding-left:10px;text-align: left;font-family: 'Open Sans', Arial, sans-serif;"> 
                                                    <div class="mktoText" id="memberRenew" mktoname="Member Renew Date">
                                                      <span style="font-size: 11px;">Renew by ${formattedCardRenewalDatemember}</span>
                                                    </div> </td> 
                                                </tr> 
#end
                                                <tr> 
                                                  <td class="extra-space2" style="line-height: 1px; font-size: 1px;" height="5">&nbsp;</td> 
                                                </tr> 
                                              </tbody> 
                                            </table> 
                                            <!--[if gte mso 9]>
                                            </v:textbox>
                                          </v:rect>
                                          <![endif]--> </td> 
                                          <td valign="top" class="block_11f26" style="background-color: #efeef4;padding: 0px 10px 0px 22px;"> 
                                            <table role="Event Agenda Tab1 Right Content" style="width: 100%; border-collapse: collapse;margin:0 auto;" border="0" cellpadding="0" cellspacing="0" width="100%" align="center"> 
                                              <tbody> 
                                                <tr> 
                                                  <td style="line-height: 1px; font-size: 1px;" height="15">&nbsp;</td> 
                                                </tr> 
                                                <tr> 
                                                  <td valign="top" style="font-family:'Open Sans',arial,sans-serif,helvetica;font-size:15px;font-weight:bold;mso-line-height: exactly;line-height:19px;color:#3d3d3d;vertical-align:top;text-align:left;"> </td> 
                                                </tr> 
#if( $membershipType.contains("A"))                                                  
                                                <tr> 
                                                  <td valign="top" style="font-family:'Open Sans',arial,sans-serif,helvetica;font-size:13px;font-weight:normal;mso-line-height: exactly;line-height:${acrLineHeight};color:#3d3d3d;vertical-align:top;text-align:left;"> 
                                                    <div class="mktoText" id="body2-para" mktoname="Paragraph">
                                                      <p style="font-family: 'Open Sans',arial,sans-serif,helvetica; font-size: 14px; font-weight: normal; color: #3d3d3d; vertical-align: top; text-align: left; margin: 0px; padding: 0px;"><span face="Open Sans, arial, helvetica, sans-serif" style="font-family: 'Open Sans', arial, helvetica, sans-serif;"> <strong>${lead.FirstName}, thank you for being a UWAA member!</strong><br /><br />Your membership is up for renewal on </span></p> 
                                                      <p style="font-family: 'Open Sans',arial,sans-serif,helvetica; font-size: 13px; font-weight: normal; color: #3d3d3d; vertical-align: top; text-align: left; margin: 0px; padding: 0px;"><span face="Open Sans, arial, helvetica, sans-serif" style="font-family: 'Open Sans', arial, helvetica, sans-serif;">${formattedParagraphRenewalDatemember}<br /><br />[[renewal.response]]</span></p>
                                                    </div> </td> 
                                                </tr> 
#else        
<tr> 
                                                  <td valign="top" style="font-family:'Open Sans',arial,sans-serif,helvetica;font-size:13px;font-weight:normal;mso-line-height: exactly;line-height:${acrLineHeight};color:#3d3d3d;vertical-align:top;text-align:left;"> 
                                                    <div class="mktoText" id="body2-para" mktoname="Paragraph">
                                                      <p style="font-family: 'Open Sans',arial,sans-serif,helvetica; font-size: 14px; font-weight: normal; color: #3d3d3d; vertical-align: top; text-align: left; margin: 0px; padding: 0px;"><span face="Open Sans, arial, helvetica, sans-serif" style="font-family: 'Open Sans', arial, helvetica, sans-serif;"> <strong>${lead.FirstName}, thank you for being a UWAA Lifetime member!</strong></span></p> 
                                                      
                                                    </div> </td> 
                                                </tr> 
#end                                        
                                                <!-- Extra space --> 
                                                <tr> 
                                                  <td style="line-height: 1px; font-size: 1px;" height="15">&nbsp;</td> 
                                                </tr> 
                                                <tr> 
                                                  <td class="extra-space2" style="line-height: 1px; font-size: 1px;" height="10">&nbsp;</td> 
                                                </tr> 
                                              </tbody> 
                                            </table> </td> 
                                        </tr> 
                                      </tbody> 
                                    </table> </td> 
                                </tr> 
                              </tbody> 
                            </table>

#else 

Not Active Member<br><br>

##ifMember
#end

##close the loop
#end


#else
No Member Object found<br><br>

#end