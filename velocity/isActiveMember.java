Custom Content should appear below here

#if ( !$uWAAMembership_cList.isEmpty() )

#foreach ($member in $uWAAMembership.cList)
#if( $member.membStatusCode == "A" )

Active Member

#else 

Not Active Member

##ifMember
#end

##foreach
#end

##ifHasModule
#end