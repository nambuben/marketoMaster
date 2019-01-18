##UWAA Membership Card Velocity Module

## Use these two values to change the visual appearance of the intro header.
#set($memberCardModuleIntroTextBackground = "#4b2e83")
#set($memberCardModuleIntroText = "your uwaa membership")

##Do not touch anything below this point, you could break the script.



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
    "lifeInstallmentStopFormat" : "MMMM, yyyy" 
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
#set($cardUpperSpacerHeight = 100)
#set($cardUpperSpacerHeightClass = "upper-space-annual")
#set($memberCardTextColor = "#3d3d3d")

#elseif( $membershipType.contains("I") || $membershipType.contains("L"))
#set($cardBackgroundURL = "https://explore.uw.edu/rs/131-AQO-225/images/2019_MemberCard_Life_600x310.jpg")
#set($cardUpperSpacerHeight = 114)
#set($cardUpperSpacerHeightClass = "upper-space-life")
#set($memberCardTextColor = "#FFFFFF")


#set($cardBackgroundURL = "https://explore.uw.edu/rs/131-AQO-225/images/2019_MemberCard_Life_600x310.jpg")
#else
Error - did not catch anything
#end

<tr>
  <td align="center" valign="top" class="text" style="background-color: ${memberCardModuleIntroTextBackground}; color: #ffffff; font-family: 'Open Sans', arial, helvetica, sans-serif; font-size: 17px; font-weight: 600; line-height: 19px; padding: 10px 15px; vertical-align: middle;">
    <div class="mktoText" id="cardIntroHeaderText" mktoname="Member Card Module Intro Language">
      <span style="font-family: 'Open Sans', arial, helvetica, sans-serif;text-transform: uppercase">${memberCardModuleIntroText}</span>
    </div>
  </td>
</tr>
<tr>
<td>
<table role="Membership Module" width="100%" cellpadding="0" cellspacing="0" style="width:100%;" class="deviceWidth_11f26 mktoModule" mktoname="Membership Information" id="membershipModule"> 
  <tbody>
    <tr>
      <td bgcolor="#FFFFFF" class="extra-space2" style="line-height: 1px; font-size: 1px;background-color:#FFFFFF;" height="15">&nbsp;</td> 
    </tr>
    <tr>
      <td bgcolor="#FFFFFF"> 
        <table width="100%" role="Inner Container" style="width: 100%; margin: 0 auto; border-collapse: collapse;" align="center" cellpadding="0" cellspacing="0">           
            <tr>
            <!-- Start Card --> 
              <td class="member-card block_11f26 center_11f26 column-2 stack-column" width="300" height="165" valign="bottom" style="background-image: url(${cardBackgroundURL});background-repeat:no-repeat;background-position: center bottom;background-size:300px;color: #000000;font-family: 'Open Sans', Arial, sans-serif;font-size: 14px;line-height: 18px;"> 
                  <!--[if (gte mso 9) | (IE)]>
                  <v:rect xmlns:v="urn:schemas-microsoft-com:vml" fill="true" stroke="false" style="width:300px;height:165px;">
                  <v:fill type="frame" src="${cardBackgroundURL}" color="#FFFFFF" />
                  <v:textbox inset="0,0,0,0">
                  <![endif]--> 
                <table align="center" width="300" height="155" cellpadding="0" cellspacing="0" style="width:300px;">
                  <tr>
                    <td valign="bottom">
                      <table align="left" width="100%" cellpadding="0" cellspacing="0" style="width:100%;">
                        <tbody>
                          <tr>
                            <td class="extra-space2" style="line-height: 1px; font-size: 1px;" height="${cardUpperSpacerHeight}">&nbsp;</td>
                          </tr> 
                          <tr>
                            <td class="member-card-text" style="padding-left:36px;text-align: left;line-height:15px;"> 
                              <div class="mktoText" id="memberNumber" mktoname="Member Number">
                                <span style="font-size: 12px;font-family: 'Open Sans', Arial, sans-serif;color:#000000">${lead.advanceID}</span>
                              </div>
                            </td>
                          </tr> 
                          <tr> 
                            <td class="member-card-text" style="padding-left:36px;text-align: left;font-family: 'Open Sans', Arial, sans-serif;font-weight: bold;color:${memberCardTextColor};line-height:15px;"> 
                              <div class="mktoText" id="memberName" mktoname="Member Name">
                                <span style="font-size: 12px;font-family: 'Open Sans', Arial, sans-serif;">${lead.FirstName} ${lead.LastName}</span>
                              </div>
                            </td> 
                        </tr>
                        #if( $membershipType.contains("A"))
                        <tr>
                          <td class="member-card-text" style="padding-left:36px;text-align: left;font-family: 'Open Sans', Arial, sans-serif;line-height:15px;"> 
                            <div class="mktoText" id="memberRenew" mktoname="Member Renew Date">
                              <span style="font-size: 11px;color:#000000">Renew by ${formattedCardRenewalDatemember}</span>
                            </div>
                          </td> 
                        </tr>
                        #end
                      </table>
                    </td>
                  </tr>                  
                  <tr>
                    <td class="extra-space2" style="line-height: 1px; font-size: 1px;" height="5">&nbsp;</td>
                  </tr>                            
                </table>    
              <!--[if (gte mso 9) | (IE)]>
              </v:textbox>
              </v:rect>
              <![endif]--> 
              </td> 
              <td valign="top" class="column-2 stack-column" style="background-color: #FFFFFF;"> 
                <table role="Member Module Renewal Information" style="width: 100%; border-collapse: collapse;margin:0 auto;" border="0" cellpadding="0" cellspacing="0" width="100%" align="center"> 
                  <tbody>
                    <!-- Extra space -->                                                 
                    <tr> 
                      <td class="extra-space2" style="line-height: 1px; font-size: 1px;" height="11">&nbsp;</td> 
                    </tr> 
                    <tr> 
                      <td valign="top" style="padding: 0 10px 0;font-family:'Open Sans',arial,sans-serif,helvetica;font-size:13px;font-weight:normal;mso-line-height: exactly;line-height:18px;color:#3d3d3d;vertical-align:top;text-align:left;">
                        <div class="mktoText" id="body2-para" mktoname="Paragraph">
                          <span style="font-family: 'Open Sans',arial,sans-serif,helvetica; font-size: 14px; font-weight: normal; color: #3d3d3d; vertical-align: top; text-align: left;"><strong>${lead.FirstName}, thank you for being a member since ${memberStartYear}!</strong></span> 
                        </div>
                      </td> 
                    </tr>
                    <tr> 
                      <td class="extra-space2" style="line-height: 1px; font-size: 1px;" height="15">&nbsp;</td> 
                    </tr> 
                    #if( $membershipType.contains("A"))                                                  
                    <tr> 
                      <td valign="top" style="padding: 0 10px 0;font-family:'Open Sans',arial,sans-serif,helvetica;font-size:13px;font-weight:normal;mso-line-height: exactly;line-height:18px;color:#3d3d3d;vertical-align:top;text-align:left;"> 
                      #if($renewalStatus == "1" || $renewalStatus == "2" && !$membershipType.contains("L") )
                      <div class="mktoText" id="body2-para" mktoname="Paragraph">
                        <span style="font-family: 'Open Sans',arial,sans-serif,helvetica; font-size: 13px; font-weight: normal; color: #3d3d3d; vertical-align: top; text-align: left;">Your membership is up for renewal on <strong>${formattedParagraphRenewalDatemember}. </strong> </span> <br>
                      </td>
                    </tr>
                    <tr> 
                      <td class="extra-space2" style="line-height: 1px; font-size: 1px;" height="15">&nbsp;</td> 
                    </tr>
                    <tr>
                      <td style="padding: 0 10px 0;">
                        <table role="Member Renewal Button" align="left" class="center_11f26" border="0" cellpadding="0" cellspacing="0" width="100%"> 
                          <tbody> 
                            <tr> 
                              <td style="background-color:#4b2e83; font-family: 'Open Sans',Arial, Helvetica, sans-serif; font-size: 14px; mso-line-height-rule: exactly; line-height: 14px; text-align: center; vertical-align: middle; color: #ffffff;display: inline-block; padding: 10px 20px;font-weight:bold;text-transform:uppercase" valign="middle"> 
                                <a aria-label="Renew Button" style="text-decoration: none;color: #ffffff; font-family: 'Open Sans',Arial, Helvetica, sans-serif;outline: none;" href="https://uw.edu/alumni/membership/be-a-member/join-or-renew?renew=true"> 
                                  <span style="font-family: 'Open Sans', arial, helvetica, sans-serif;">Renew Today</span> 
                                </a> 
                              </td> 
                            </tr>
                          </tbody> 
                        </table>
                      </div>
                      #end
                    </td> 
                  </tr> 
                  #else                                   
                  #end                                        
                  #if($membershipType.contains("I"))
                  <tr> 
                    <td valign="top" style="padding: 0 10px 0;font-family:'Open Sans',arial,sans-serif,helvetica;font-size:13px;font-weight:normal;mso-line-height: exactly;line-height:18px;color:#3d3d3d;vertical-align:top;text-align:left;"> 
                      <div class="mktoText" id="body2-para" mktoname="Paragraph">
                        <span style="font-family: 'Open Sans',arial,sans-serif,helvetica; font-size: 14px; font-weight: normal; color: #3d3d3d; vertical-align: top; text-align: left;">You have paid $${amountPaid} toward your pledge of $${amountTotal}, becoming a paid-in-full life member.  Your last payment will be in ${lifeInstallmentStop}.</span>
                      </div>
                    </td> 
                  </tr> 
                  #end
                  <!-- Extra space -->                                                 
                  <tr> 
                    <td class="extra-space2" style="line-height: 1px; font-size: 1px;" height="15">&nbsp;</td> 
                  </tr> 
                </tbody> 
              </table>
            </td> 
          </tr> 
        </tbody> 
      </table> 
      </td> 
    </tr> 
  </tbody> 
</table>
<tr>

        #else 

Not Active Member<br><br>

##ifMember
#end

##close the loop
#end


#else

#end