
import React, { useEffect } from 'react';
import Navbar from "@/components/Navbar";
import Hero from "@/components/Hero";
import VaccineInfo from "@/components/VaccineInfo";
import LocationFinder from "@/components/LocationFinder";
import BookingSection from "@/components/BookingSection";
import AboutUs from "@/components/AboutUs";
import Footer from "@/components/Footer";
import VaccinationGuide from "@/components/VaccinationGuide";
import PriceList from "@/components/PriceList";
import ServiceInfo from "@/components/ServiceInfo";
import { 
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger
} from "@/components/ui/accordion";

const Index = () => {
  // Smooth scroll for anchor links
  useEffect(() => {
    const handleAnchorClick = (e: MouseEvent) => {
      const target = e.target as HTMLAnchorElement;
      if (target.tagName === 'A' && target.getAttribute('href')?.startsWith('#')) {
        e.preventDefault();
        const id = target.getAttribute('href')?.substring(1);
        if (id) {
          const element = document.getElementById(id);
          if (element) {
            window.scrollTo({
              top: element.offsetTop - 100,
              behavior: 'smooth'
            });
          }
        }
      }
    };

    document.addEventListener('click', handleAnchorClick);
    return () => document.removeEventListener('click', handleAnchorClick);
  }, []);

  return (
    <div className="min-h-screen flex flex-col bg-gray-50">
      <Navbar />
      <main className="flex-grow">
        <Hero />
        <div className="bg-white py-10">
          <div className="container mx-auto px-4">
            <div className="max-w-3xl mx-auto">
              <Accordion type="single" collapsible className="w-full">
                <AccordionItem value="item-1">
                  <AccordionTrigger className="text-lg font-semibold">
                    Lợi ích của tiêm chủng là gì?
                  </AccordionTrigger>
                  <AccordionContent>
                    Tiêm chủng giúp bảo vệ trẻ khỏi nhiều bệnh nguy hiểm có thể gây tử vong hoặc di chứng suốt đời. 
                    Việc tiêm chủng đầy đủ và đúng lịch giúp tăng cường hệ miễn dịch cho trẻ, bảo vệ cộng đồng 
                    và tiết kiệm chi phí điều trị bệnh.
                  </AccordionContent>
                </AccordionItem>
                <AccordionItem value="item-2">
                  <AccordionTrigger className="text-lg font-semibold">
                    Trẻ em cần tiêm những loại vaccine nào?
                  </AccordionTrigger>
                  <AccordionContent>
                    Theo chương trình tiêm chủng mở rộng quốc gia, trẻ em cần được tiêm các loại vaccine phòng 
                    các bệnh như: Lao, Viêm gan B, Bạch hầu, Ho gà, Uốn ván, Bại liệt, Hib, Sởi, Rubella, 
                    Viêm não Nhật Bản, và các loại vaccine khác tùy theo nhu cầu và điều kiện sức khỏe.
                  </AccordionContent>
                </AccordionItem>
                <AccordionItem value="item-3">
                  <AccordionTrigger className="text-lg font-semibold">
                    Quy trình tiêm chủng tại trung tâm như thế nào?
                  </AccordionTrigger>
                  <AccordionContent>
                    Quy trình tiêm chủng bao gồm: Đăng ký thông tin → Khám sàng lọc → Tư vấn và chỉ định tiêm → 
                    Thực hiện tiêm → Theo dõi phản ứng sau tiêm 30 phút → Hẹn lịch tiêm mũi tiếp theo.
                  </AccordionContent>
                </AccordionItem>
              </Accordion>
            </div>
          </div>
        </div>
        <AboutUs />
        <ServiceInfo />
        <VaccineInfo />
        <PriceList />
        <VaccinationGuide />
        <LocationFinder />
        <BookingSection />
      </main>
      <Footer />
    </div>
  );
};

export default Index;
