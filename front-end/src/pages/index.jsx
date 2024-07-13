import React from "react";
import styled from "styled-components";
import { useGeoLocation } from "../hooks/useGeoLocation";

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
  const { location: geoLocation, error } = useGeoLocation({
    enableHighAccuracy: true,
    timeout: 1000 * 10,
    maximumAge: 1000 * 3600 * 24,
  });

  const latitude = geoLocation.latitude;
  const longitude = geoLocation.longitude;

  if (latitude && longitude) {
    const { kakao } = window;

    var mapContainer = document.getElementById("map"), // 지도를 표시할 div
      mapOption = {
        center: new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
        mapTypeId: kakao.maps.MapTypeId.ROADMAP, // 지도종류
      };

    // 지도 생성
    var map = new kakao.maps.Map(mapContainer, mapOption);
  }

  return (
    <Container>
      <MapContainer id="map"></MapContainer>
      {error && <p>Error: {error}</p>}
    </Container>
  );
};

export default Main;
