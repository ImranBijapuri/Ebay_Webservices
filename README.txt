Endpoints

1) This endpoint allows you add new Ads to the system
   Sample call http://localhost:8084/Ebay_Webservices/rest/ads
   Request Type POST //Parameters -  form data "adText"

2) This endpoint allows you to view Ads as per id
   Sample call http://localhost:8084/Ebay_Webservices/rest/ads/3
   Request Type GET
   Parameters -  Path Param adId

3) This endpoint allows you to delete Ads as per id
   Sample call http://localhost:8084/Ebay_Webservices/rest/ads/7
   Request Type DELETE
   Parameters -  Path Param adId

4) This endpoint allows you to update an Ads click count as per id
   Sample call http://localhost:8084/Ebay_Webservices/rest/ads/trackclick/1
   Request Type PUT
   Parameters -  Path Param adId

5) This endpoint allows you to update an Ads impression count as per id
   Sample call http://localhost:8084/Ebay_Webservices/rest/ads/trackimpression/1
   Request Type PUT
   Parameters -  Path Param adId

6)  This endpoint allows you to get an Ad from the system as per out implementation of smart adserver
    Sample call http://localhost:8084/Ebay_Webservices/rest/ads/getAd
    Request Type GET




Below are the SQL stored procedures used.

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAd`()
BEGIN

select x.ad_id,x.impressions,x.clicks,x.CTR,x.ad_text from
    ((select a.ad_id,a.impressions,a.clicks,max(a.clicks/a.impressions) as CTR,ads.ad_text from adserving a,ad ads
where ads.ad_id = a.ad_id group by a.ad_id desc limit 1)

	UNION
    
    (select a.ad_id,a.impressions,a.clicks,(a.clicks/a.impressions) as CTR,ads.ad_text FROM adserving a,ad ads
   where ads.ad_id = a.ad_id group by a.ad_id ORDER BY RAND() LIMIT 1))
   as x
    ORDER BY RAND() LIMIT 1;

END



/**************************************************/

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_ad`(vtext varchar(200))
BEGIN
	insert into ad (ad_text) values (vtext);
    insert into adserving values ((SELECT ad_id FROM ad ORDER BY ad_id DESC LIMIT 1), 0, 0, 0.0); 
END


/**************************************************/


CREATE DEFINER=`root`@`localhost` PROCEDURE `updatetrackclick`(vid int(11))
BEGIN
	DECLARE varclick INT;   
    set varclick = (select clicks from adserving where ad_id = vid);
    update adserving set clicks = varclick + 1 where ad_id = vid;
END


/************************************************/

CREATE DEFINER=`root`@`localhost` PROCEDURE `updatetrackimpression`(vid int(11))
BEGIN
	DECLARE varimpression INT;   
    set varimpression = (select impressions from adserving where ad_id = vid);
    update adserving set impressions = varimpression + 1 where ad_id = vid;
END



/**********************************************/