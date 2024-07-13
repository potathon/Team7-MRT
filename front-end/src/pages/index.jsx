import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { useGeoLocation } from "../hooks/useGeoLocation";
import sampleData from "../data/sampleData.json";
import sampleImg from "../data/sampleImg.png";
import { PanToButton } from "../components/PanToButton";
import CategoryButton from "../components/CategoryButton";

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

const CategoryButtonsWrapper = styled.div`
  width: 300px;
  height: 32px;
  box-sizing: content-box;
  background-color: rgb(255, 255, 255);
  padding: 2px;
  border-radius: 3px;
  box-shadow: rgba(0, 0, 0, 0.15) 0px 2px 2px 0px;
  position: absolute;
  left: 40px;
  top: 20px;
  z-index: 1;
`;

const Main = () => {
  const [map, setMap] = useState(null);
  const [data, setData] = useState([]);
  const [markers, setMarkers] = useState([]);
  const { kakao } = window;

  const [selectedCategory, setSelectedCategory] = useState("all");
  const categories = [
    { category: "all", name: "전체 보기" },
    { category: "normal", name: "① 일반쓰레기" },
    { category: "recycle", name: "② 재활용쓰레기" },
  ];

  const { location: geoLocation, error } = useGeoLocation({
    enableHighAccuracy: true,
    timeout: 1000 * 10,
    maximumAge: 1000 * 3600 * 24,
  });

  const latitude = geoLocation.latitude;
  const longitude = geoLocation.longitude;

  useEffect(() => {
    if (latitude && longitude) {
      initializeMap(37.5113, 127.1052); // set initial location to Seokchon Lake
    }
  }, [latitude, longitude]);

  useEffect(() => {
    if (map) {
      loadMapData(selectedCategory);
    }
  }, [map, selectedCategory]);

  useEffect(() => {
    if (map && data.length > 0) {
      updateMarkers();
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

  const loadMapData = (name) => {
    // TODO: 서버에 데이터 요청 로직 추가
    let filteredData = sampleData;

    if (name !== "all") {
      const category = categories.find((item) => item.category === name);
      filteredData = sampleData.filter((item) => item.종류 === category.name);
    }

    setData(filteredData);
  };

  const updateMarkers = () => {
    if (markers) {
      markers.forEach((marker) => marker.setMap(null));
      setMarkers([]);
    }

    // 새로운 마커 추가
    data.forEach((item) => {
      const position = new kakao.maps.LatLng(item.Latitude, item.Longitude);
      const imageSize = new kakao.maps.Size(24, 35);
      const markerImage = new kakao.maps.MarkerImage(sampleImg, imageSize);

      const marker = new kakao.maps.Marker({
        map: map,
        position: position,
        title: item.세부위치,
        image: markerImage,
      });

      setMarkers((prev) => [...prev, marker]);
    });
  };

  return (
    <Container>
      <MapContainer id="map"></MapContainer>
      <CategoryButtonsWrapper>
        {categories.map((item) => {
          return (
            <CategoryButton
              key={item.category}
              name={item.name}
              category={item.category}
              isSelected={selectedCategory === item.category}
              onClick={() => setSelectedCategory(item.category)}
            />
          );
        })}
      </CategoryButtonsWrapper>
      <PanToButton map={map} kakao={kakao} lat={latitude} lng={longitude} />
      {error && <p>Error: {error}</p>}
    </Container>
  );
};

export default Main;
