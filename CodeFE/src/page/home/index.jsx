import React from "react";
import BannerBackground from "../../assets/background.png";
import "./index.scss";
import { Col, Row } from "antd";

function Home() {
  return (<div className="home-container">
    <h1 className="title">Sunside Koi Care</h1>
    <Row className="home-container-1" align="middle" gutter={30}>
      <Col span={12}>
        <img src={BannerBackground} alt="BannerBack" />
      </Col>
      <Col span={12}>
        <h1>Hhhhh</h1>
        <p>
          Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ipsa voluptas cum perferendis numquam quae sapiente dolorum recusandae debitis perspiciatis a, mollitia quas illum reprehenderit neque tempora hic, eaque in beatae.
        </p>
      </Col>
    </Row>

    <Row className="home-container-2" align="middle" gutter={30}>
      <Col span={12}>
        <h1>Hhhhh</h1>
        <p>
          Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ipsa voluptas cum perferendis numquam quae sapiente dolorum recusandae debitis perspiciatis a, mollitia quas illum reprehenderit neque tempora hic, eaque in beatae.
        </p>
      </Col>
      <Col span={12}>
        <img src={BannerBackground} alt="BannerBack" />
      </Col>
    </Row>

    <Row className="home-container-3" align="middle" gutter={30}>
      <Col span={12}>
        <img src={BannerBackground} alt="BannerBack" />
      </Col>
      <Col span={12}>
        <h1>Hhhhh</h1>
        <p>
          Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ipsa voluptas cum perferendis numquam quae sapiente dolorum recusandae debitis perspiciatis a, mollitia quas illum reprehenderit neque tempora hic, eaque in beatae.
        </p>
      </Col>
    </Row>
    </div>
  );
}

export default Home;

