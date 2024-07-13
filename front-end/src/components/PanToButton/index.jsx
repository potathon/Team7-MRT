import React from "react";
import styled from "styled-components";

const StyledButton = styled.button`
  z-index: 1;
  position: absolute;
  bottom: 30px; /* 화면 하단으로부터의 거리 */
  right: 30px; /* 화면 오른쪽으로부터의 거리 */
  background-color: white; /* Primary color */
  color: black; /* Text color */
  border: none;
  width: 60px;
  height: 60px;
  border-radius: 50%;
  font-size: 13px;
  cursor: pointer;
  transition: background-color 0.3s;
  box-shadow: 0 0 4px 5px rgba(0, 123, 255, 0.5);

  &:hover {
    background-color: #49a1ff; /* Darker shade of primary color */
  }

  &:focus {
    outline: none;
    box-shadow: 0 0 0 3px rgba(91, 156, 229, 0.5); /* Focus outline */
  }

  &:active {
    background-color: #3c8eea; /* Even darker shade for active state */
  }
`;

export const PanToButton = ({ kakao, map, lat, lng }) => {
  const handleClick = (e) => {
    e.preventDefault();

    const moveLatLon = new kakao.maps.LatLng(lat, lng);
    map.panTo(moveLatLon);
  };

  return (
    <>
      <StyledButton onClick={handleClick}>내 위치</StyledButton>
    </>
  );
};
