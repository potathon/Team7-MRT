import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { useGeoLocation } from "../hooks/useGeoLocation";
import sampleData from "../data/sampleData.json";
import sampleImg from "../data/sampleImg.png";

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
`;

const MapContainer = styled.div`
  width: 100%;
  height: 100%;
`;

const Main = () => {
  const [map, setMap] = useState(null);
  const [data, setData] = useState([]);
  const { kakao } = window;

  const { location: geoLocation, error } = useGeoLocation({
    enableHighAccuracy: true,
    timeout: 1000 * 10,
    maximumAge: 1000 * 3600 * 24,
  });

  const latitude = geoLocation.latitude;
  const longitude = geoLocation.longitude;

  useEffect(() => {
    if (latitude && longitude) {
      initializeMap(latitude, longitude);
    }
  }, [latitude, longitude]);

  useEffect(() => {
    if (map) {
      loadMapData();
    }
  }, [map]);

  useEffect(() => {
    if (map && data.length > 0) {
      displayMarkers();
    }
  }, [data]);

  const initializeMap = (lat, lon) => {
    const mapContainer = document.getElementById("map");
    const mapOption = {
      center: new kakao.maps.LatLng(lat, lon),
      level: 3,
      mapTypeId: kakao.maps.MapTypeId.ROADMAP,
    };

    const newMap = new kakao.maps.Map(mapContainer, mapOption);
    setMap(newMap);
  };

  const loadMapData = () => {
    // TODO: 서버에 데이터 요청 로직 추가
    setData(sampleData);
  };

  const displayMarkers = () => {
    data.forEach((item) => {
      const position = new kakao.maps.LatLng(item.Latitude, item.Longitude);
      const imageSize = new kakao.maps.Size(24, 35);
      const markerImage = new kakao.maps.MarkerImage(sampleImg, imageSize);

      new kakao.maps.Marker({
        map: map,
        position: position,
        title: item.세부위치,
        image: markerImage,
      });
    });
  };

  return (
    <Container>
      <MapContainer id="map"></MapContainer>
      {error && <p>Error: {error}</p>}
    </Container>
  );
};

export default Main;
