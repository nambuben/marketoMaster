#set($activeMemberCode = "A")

#if ( !$uWAAMembership_cList.isEmpty() )


#foreach($member in $uWAAMembership_cList)



#if( $member.membStatusCode.equals($activeMemberCode) )

## Formats our date object in order to display on the card.
#set( $cardDateOptions = {  
  "formats" : {  
    "object" : "yyyy-MM-dd",  
    "card" : "MM/d/yy",
    "paragraph" :  "MMMM dd, yyyy",
    "lifeInstallmentStop" : "MMMM, yyyy" 
  },  
  "timezones" : {  
    "object" : "America/Los_Angeles",  
    "card" : "America/Los_Angeles",
    "paragraph" : "America/Los_Angeles",
    "lifeInstallmentStopFormat" : "America/Los_Angeles"
  },  
  "locale" : $date.getLocale()  
} )  
#set( $expirationDatelike = $member.membStopDate )  

#set( $expirationDate = $convert.parseDate(  
  $expirationDatelike,  
  $cardDateOptions.formats.object,  
  $cardDateOptions.locale,  
  $date.getTimeZone().getTimeZone($cardDateOptions.timezones.object)  
) )  



#set( $formattedCardRenewalDatemember = $date.format(  
  $cardDateOptions.formats.card,  
  $expirationDate,  
  $cardDateOptions.locale,  
  $date.getTimeZone().getTimeZone($cardDateOptions.timezones.card)  
) )  

#set( $formattedParagraphRenewalDatemember = $date.format(  
  $cardDateOptions.formats.paragraph,  
  $expirationDate,  
  $cardDateOptions.locale,  
  $date.getTimeZone().getTimeZone($cardDateOptions.timezones.paragraph)  
) )

#set( $lifeInstallmentStop = $date.format(  
  $cardDateOptions.formats.lifeInstallmentStopFormat,  
  $expirationDate,  
  $cardDateOptions.locale,  
  $date.getTimeZone().getTimeZone($cardDateOptions.timezones.lifeInstallmentStopFormat)  
) )



##Set up Member Start Year
#set( $memberStartDateRaw = $member.membDateJoined )  
#set( $memberStartDateOptions = {  
  "formats" : {  
    "object" : "yyyy-MM-dd",  
    "display" : "yyyy"
  },  
  "timezones" : {  
    "object" : "America/Los_Angeles",
    "display" : "America/Los_Angeles"
  },  
  "locale" : $date.getLocale()  
} ) 

#set( $memberStartDate = $convert.parseDate(  
  $memberStartDateRaw,  
  $cardDateOptions.formats.object,  
  $cardDateOptions.locale,  
  $date.getTimeZone().getTimeZone($cardDateOptions.timezones.object)  
) )  
#set( $memberStartYear = $date.format(  
  $memberStartDateOptions.formats.display,  
  $memberStartDate,  
  $memberStartDateOptions.locale,  
  $date.getTimeZone().getTimeZone($memberStartDateOptions.timezones.display)  
) )
 
 
##Set up data for installment members, for showing amount paid to date, and the total amount they will pay.
#set($amountPaid = $member.membAmtPaid)
#set($amountTotal = $member.membAmountTotal)


##Does the Life/Annual Check - setting background for the card, and determining which text should appear
#set($membershipType = $member.membTypeCode)
#set($renewalStatus = $member.membRenewalStatus)


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
                                                      <p style="font-family: 'Open Sans',arial,sans-serif,helvetica; font-size: 14px; font-weight: normal; color: #3d3d3d; vertical-align: top; text-align: left; margin: 0px; padding: 0px;"><span face="Open Sans, arial, helvetica, sans-serif" style="font-family: 'Open Sans', arial, helvetica, sans-serif;"> <strong>${lead.FirstName}, thank you for being a member since ${memberStartYear}!</strong> </span></p> 
#if($renewalStatus == "1" || $renewalStatus == "2" && !$membershipType.contains("L") )                                                    
                                                      <p style="font-family: 'Open Sans',arial,sans-serif,helvetica; font-size: 13px; font-weight: normal; color: #3d3d3d; vertical-align: top; text-align: left; margin: 0px; padding: 0px;"><span face="Open Sans, arial, helvetica, sans-serif" style="font-family: 'Open Sans', arial, helvetica, sans-serif;">Your membership is up for renewal on ${formattedParagraphRenewalDatemember} </span></p>
                                                      <table role="Member Renewal Button" align="left" border="0" cellpadding="0" cellspacing="0"> 
                                                        <tbody> 
                                                          <tr> 
                                                            <td style="background-color:#4b2e83; font-family: 'Open Sans',Arial, Helvetica, sans-serif; font-size: 14px; mso-line-height-rule: exactly; line-height: 14px; text-align: center; vertical-align: middle; color: #ffffff;display: inline-block; padding: 10px 
                                                          20px;font-weight:bold;text-transform:uppercase;" valign="middle"> <a aria-label="Event Agenda Button1" style="text-decoration: none;color: #ffffff; font-family: 'Open Sans',Arial, Helvetica, sans-serif;outline: none;" href="https://uw.edu/alumni/membership/be-a-member/join-or-renew?renew=true"> <span style="font-family: 'Open Sans', arial, 
                                                          helvetica, sans-serif;">Renew Today</span> </a> </td> 
                                                          </tr>
                                                        </tbody> 
                                                      </table>


#end
                                                    </div> </td> 
                                                </tr> 
#else        
                                                <tr> 
                                                  <td valign="top" style="font-family:'Open Sans',arial,sans-serif,helvetica;font-size:13px;font-weight:normal;mso-line-height: exactly;line-height:${acrLineHeight};color:#3d3d3d;vertical-align:top;text-align:left;"> 
                                                    <div class="mktoText" id="body2-para" mktoname="Paragraph">
                                                      <p style="font-family: 'Open Sans',arial,sans-serif,helvetica; font-size: 14px; font-weight: normal; color: #3d3d3d; vertical-align: top; text-align: left; margin: 0px; padding: 0px;"><span face="Open Sans, arial, helvetica, sans-serif" style="font-family: 'Open Sans', arial, helvetica, sans-serif;"> <strong>${lead.FirstName}, thank you for being a member since ${memberStartYear}!</strong></span></p> 
                                                      
                                                    </div> </td> 
                                                </tr> 
#end                                        
#if($membershipType.contains("I"))
                                                <tr> 
                                                  <td valign="top" style="font-family:'Open Sans',arial,sans-serif,helvetica;font-size:13px;font-weight:normal;mso-line-height: exactly;line-height:${acrLineHeight};color:#3d3d3d;vertical-align:top;text-align:left;"> 
                                                    <div class="mktoText" id="body2-para" mktoname="Paragraph">
                                                      <p style="font-family: 'Open Sans',arial,sans-serif,helvetica; font-size: 14px; font-weight: normal; color: #3d3d3d; vertical-align: top; text-align: left; margin: 0px; padding: 0px;"><span face="Open Sans, arial, helvetica, sans-serif" style="font-family: 'Open Sans', arial, helvetica, sans-serif;"> You have paid $${amountPaid} toward your pledge of $${amountTotal}, becoming a paid-in-full life member.  Your last payment will be in ${lifeInstallmentStop}.</span></p> 
                                                      
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