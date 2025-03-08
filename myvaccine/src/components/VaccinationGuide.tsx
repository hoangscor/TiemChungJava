
import React, { useEffect, useRef, useState } from 'react';
import { BookOpen, ChevronDown, ChevronUp } from 'lucide-react';
import { Button } from "@/components/ui/button";
import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from "@/components/ui/accordion";
import {
  Tabs,
  TabsContent,
  TabsList,
  TabsTrigger,
} from "@/components/ui/tabs";

const guides = {
  general: [
    {
      question: "Trẻ nên tiêm chủng những loại vaccine nào?",
      answer: "Trẻ em cần được tiêm chủng đầy đủ các loại vaccine trong chương trình tiêm chủng mở rộng và các vaccine khuyến nghị khác tùy theo độ tuổi, bao gồm: vaccine 6 trong 1, Rotavirus, Phế cầu, Viêm não Nhật Bản, Thủy đậu, HPV, v.v."
    },
    {
      question: "Khi nào trẻ nên bắt đầu tiêm chủng?",
      answer: "Trẻ nên bắt đầu tiêm chủng ngay từ khi mới sinh với mũi tiêm đầu tiên là vaccine viêm gan B. Sau đó, việc tiêm chủng sẽ tiếp tục theo lịch khuyến nghị ở các tháng tuổi tiếp theo."
    },
    {
      question: "Cách chuẩn bị cho trẻ trước khi tiêm chủng?",
      answer: "Trước khi tiêm chủng, phụ huynh nên cho trẻ ăn uống đầy đủ, mặc trang phục thoải mái, dễ cởi. Nên thông báo cho bác sĩ về tình trạng sức khỏe hiện tại của trẻ, tiền sử dị ứng hoặc phản ứng với các mũi tiêm trước đó."
    },
    {
      question: "Những tác dụng phụ có thể gặp sau tiêm chủng?",
      answer: "Sau tiêm chủng, trẻ có thể gặp một số tác dụng phụ nhẹ như sốt nhẹ, đau tại chỗ tiêm, mệt mỏi, khó chịu. Những triệu chứng này thường tự khỏi sau 1-2 ngày. Nếu trẻ sốt cao trên 38.5°C hoặc có các biểu hiện bất thường, cần đưa trẻ đến cơ sở y tế ngay."
    },
    {
      question: "Cần làm gì khi trẻ sốt sau tiêm?",
      answer: "Khi trẻ sốt sau tiêm, phụ huynh có thể cho trẻ uống thuốc hạ sốt theo chỉ định của bác sĩ, cho trẻ mặc quần áo thoáng mát, uống nhiều nước và nghỉ ngơi. Nếu sốt kéo dài hoặc sốt cao trên 38.5°C, cần đưa trẻ đến cơ sở y tế."
    }
  ],
  schedule: [
    {
      question: "Lịch tiêm chủng cho trẻ từ 0-12 tháng tuổi?",
      answer: "Trẻ từ 0-12 tháng tuổi cần được tiêm: Viêm gan B (0, 2, 4 tháng), BCG (0-1 tháng), Bại liệt (2, 3, 4 tháng), DPT-VGB-Hib (2, 3, 4 tháng), Rotavirus (2, 3, 4 tháng), Phế cầu (2, 4, 6 tháng), Viêm não Nhật Bản (tiêm từ 12 tháng)."
    },
    {
      question: "Lịch tiêm chủng cho trẻ từ 1-5 tuổi?",
      answer: "Trẻ từ 1-5 tuổi cần được tiêm nhắc: Viêm não Nhật Bản (mũi 2 sau mũi 1 khoảng 1-2 tuần, mũi 3 sau mũi 2 một năm), Sởi-Quai bị-Rubella (12-18 tháng), DPT mũi 4 (18 tháng), Bại liệt mũi 4 (18 tháng), Thủy đậu (12-18 tháng), Viêm gan A (12, 18 tháng)."
    },
    {
      question: "Lịch tiêm chủng cho trẻ trên 5 tuổi?",
      answer: "Trẻ trên 5 tuổi cần được tiêm nhắc: DT (4-6 tuổi), Sởi-Quai bị-Rubella (4-6 tuổi), HPV (nữ, 9-14 tuổi), Viêm não Nhật Bản (mũi nhắc lại), Uốn ván-Bạch hầu (10 tuổi, sau đó 10 năm/lần)."
    },
    {
      question: "Nếu trẻ trễ lịch tiêm chủng thì phải làm sao?",
      answer: "Nếu trẻ trễ lịch tiêm chủng, phụ huynh nên đưa trẻ đi tiêm bù càng sớm càng tốt. Bác sĩ sẽ đánh giá và điều chỉnh lịch tiêm phù hợp với tình trạng của trẻ mà không cần bắt đầu lại từ đầu."
    },
    {
      question: "Có thể tiêm nhiều loại vaccine cùng lúc cho trẻ không?",
      answer: "Có thể tiêm nhiều loại vaccine cùng lúc cho trẻ, việc này không làm tăng tác dụng phụ và không ảnh hưởng đến hiệu quả của vaccine. Tuy nhiên, nên tuân theo lịch tiêm được bác sĩ khuyến nghị và không tự ý kết hợp các loại vaccine."
    }
  ],
  safety: [
    {
      question: "Làm thế nào để biết vaccine an toàn?",
      answer: "Vaccine được cung cấp tại các cơ sở y tế uy tín đều đã trải qua quá trình nghiên cứu, thử nghiệm nghiêm ngặt và được cơ quan quản lý phê duyệt. Chúng tôi chỉ sử dụng các vaccine đạt tiêu chuẩn WHO-GMP, được bảo quản trong dây chuyền lạnh đúng quy định."
    },
    {
      question: "Khi nào không nên tiêm chủng cho trẻ?",
      answer: "Không nên tiêm chủng cho trẻ khi trẻ đang sốt cao (trên 38.5°C), đang mắc bệnh cấp tính, có tiền sử dị ứng nặng với thành phần của vaccine, đang dùng thuốc ức chế miễn dịch, hoặc có bệnh lý về hệ miễn dịch. Cần tham khảo ý kiến bác sĩ trước khi tiêm."
    },
    {
      question: "Làm thế nào để nhận biết phản ứng nặng sau tiêm chủng?",
      answer: "Các dấu hiệu cần chú ý là: sốt cao trên 39°C kéo dài, co giật, khó thở, phát ban toàn thân, sưng đau nhiều tại chỗ tiêm, li bì, bỏ bú. Khi gặp các dấu hiệu này, cần đưa trẻ đến cơ sở y tế ngay lập tức."
    },
    {
      question: "Làm thế nào để theo dõi trẻ sau tiêm chủng?",
      answer: "Sau tiêm chủng, phụ huynh nên ở lại cơ sở y tế ít nhất 30 phút để theo dõi phản ứng. Tại nhà, cần theo dõi nhiệt độ của trẻ, vị trí tiêm, và các biểu hiện bất thường. Cho trẻ uống nhiều nước và nghỉ ngơi đầy đủ."
    },
    {
      question: "Vaccine có gây ra tự kỷ không?",
      answer: "Không có bằng chứng khoa học nào cho thấy vaccine gây ra tự kỷ. Đây là một quan niệm sai lầm xuất phát từ một nghiên cứu đã bị bác bỏ. Nhiều nghiên cứu quy mô lớn sau đó đã khẳng định không có mối liên hệ giữa tiêm chủng và tự kỷ."
    }
  ]
};

const VaccinationGuide = () => {
  const [activeTab, setActiveTab] = useState("general");
  const sectionRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const observer = new IntersectionObserver(
      ([entry]) => {
        if (entry.isIntersecting) {
          entry.target.classList.add('opacity-100');
          entry.target.classList.remove('opacity-0');
        }
      },
      { threshold: 0.1 }
    );

    const currentSectionRef = sectionRef.current;
    if (currentSectionRef) {
      observer.observe(currentSectionRef);
    }

    return () => {
      if (currentSectionRef) {
        observer.unobserve(currentSectionRef);
      }
    };
  }, []);

  return (
    <section id="guides" className="py-20 bg-blue-50">
      <div 
        ref={sectionRef}
        className="section-container opacity-0 transition-opacity duration-1000"
      >
        <div className="text-center mb-16">
          <div className="inline-flex items-center rounded-full px-3 py-1 text-sm font-medium bg-blue-50 text-primary mb-6">
            <BookOpen className="h-4 w-4 mr-1" />
            <span>Cẩm nang tiêm chủng</span>
          </div>
          <h2 className="text-balance font-medium mb-6">
            Những điều cần biết về <span className="text-primary">tiêm chủng</span>
          </h2>
          <p className="text-gray-600 max-w-xl mx-auto">
            Tổng hợp những thông tin hữu ích giúp phụ huynh hiểu rõ hơn về quá trình tiêm chủng, 
            chuẩn bị tốt nhất cho con yêu và xử lý các tình huống có thể xảy ra.
          </p>
        </div>

        <div className="glass rounded-xl p-6 sm:p-8">
          <Tabs value={activeTab} onValueChange={setActiveTab} className="w-full">
            <TabsList className="grid w-full grid-cols-1 md:grid-cols-3 mb-8">
              <TabsTrigger value="general">Thông tin chung</TabsTrigger>
              <TabsTrigger value="schedule">Lịch tiêm chủng</TabsTrigger>
              <TabsTrigger value="safety">An toàn tiêm chủng</TabsTrigger>
            </TabsList>
            
            <TabsContent value="general">
              <Accordion type="single" collapsible className="w-full">
                {guides.general.map((item, index) => (
                  <AccordionItem key={index} value={`item-${index}`}>
                    <AccordionTrigger className="text-left font-medium text-lg">
                      {item.question}
                    </AccordionTrigger>
                    <AccordionContent className="text-gray-600">
                      {item.answer}
                    </AccordionContent>
                  </AccordionItem>
                ))}
              </Accordion>
            </TabsContent>
            
            <TabsContent value="schedule">
              <Accordion type="single" collapsible className="w-full">
                {guides.schedule.map((item, index) => (
                  <AccordionItem key={index} value={`item-${index}`}>
                    <AccordionTrigger className="text-left font-medium text-lg">
                      {item.question}
                    </AccordionTrigger>
                    <AccordionContent className="text-gray-600">
                      {item.answer}
                    </AccordionContent>
                  </AccordionItem>
                ))}
              </Accordion>
            </TabsContent>
            
            <TabsContent value="safety">
              <Accordion type="single" collapsible className="w-full">
                {guides.safety.map((item, index) => (
                  <AccordionItem key={index} value={`item-${index}`}>
                    <AccordionTrigger className="text-left font-medium text-lg">
                      {item.question}
                    </AccordionTrigger>
                    <AccordionContent className="text-gray-600">
                      {item.answer}
                    </AccordionContent>
                  </AccordionItem>
                ))}
              </Accordion>
            </TabsContent>
          </Tabs>
          
          <div className="mt-12 text-center">
            <Button className="bg-primary hover:bg-primary/90 text-white btn-hover-effect">
              Xem thêm hướng dẫn
            </Button>
          </div>
        </div>
      </div>
    </section>
  );
};

export default VaccinationGuide;
