
import React, { useEffect, useRef } from 'react';
import { Syringe, Shield, HeartPulse, Baby, UserCheck, CalendarDays } from 'lucide-react';
import { Button } from "@/components/ui/button";

const services = [
  {
    id: 1,
    title: 'Gói tiêm chủng cơ bản',
    description: 'Bao gồm các mũi tiêm bắt buộc theo chương trình tiêm chủng mở rộng quốc gia.',
    icon: Shield,
    color: 'bg-blue-100',
    iconColor: 'text-blue-700',
  },
  {
    id: 2,
    title: 'Gói tiêm chủng nâng cao',
    description: 'Cung cấp thêm các loại vaccine hiện đại giúp phòng ngừa nhiều bệnh nguy hiểm khác.',
    icon: HeartPulse,
    color: 'bg-green-100',
    iconColor: 'text-green-700',
  },
  {
    id: 3,
    title: 'Tiêm chủng trọn gói',
    description: 'Chương trình tiêm chủng đầy đủ với lịch tiêm tối ưu theo từng giai đoạn phát triển.',
    icon: Baby,
    color: 'bg-purple-100',
    iconColor: 'text-purple-700',
  },
  {
    id: 4,
    title: 'Tiêm chủng cá thể hóa',
    description: 'Lịch tiêm được thiết kế riêng phù hợp với tình trạng sức khỏe và nhu cầu của từng trẻ.',
    icon: UserCheck,
    color: 'bg-orange-100',
    iconColor: 'text-orange-700',
  },
  {
    id: 5,
    title: 'Tiêm chủng người lớn',
    description: 'Dịch vụ tiêm chủng dành cho người trưởng thành và phụ nữ mang thai.',
    icon: UserCheck,
    color: 'bg-red-100',
    iconColor: 'text-red-700',
  },
  {
    id: 6,
    title: 'Tiêm chủng theo mùa',
    description: 'Các loại vaccine phòng bệnh theo mùa như cúm, viêm phổi...',
    icon: CalendarDays,
    color: 'bg-yellow-100',
    iconColor: 'text-yellow-700',
  },
];

const ServiceInfo = () => {
  const sectionRef = useRef<HTMLDivElement>(null);
  const cardsRef = useRef<(HTMLDivElement | null)[]>([]);

  useEffect(() => {
    const observer = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            entry.target.classList.add('opacity-100');
            entry.target.classList.remove('opacity-0');
          }
        });
      },
      { threshold: 0.1 }
    );

    const currentSectionRef = sectionRef.current;
    if (currentSectionRef) {
      observer.observe(currentSectionRef);
    }

    cardsRef.current.forEach((card) => {
      if (card) {
        observer.observe(card);
      }
    });

    return () => {
      if (currentSectionRef) {
        observer.unobserve(currentSectionRef);
      }
      cardsRef.current.forEach((card) => {
        if (card) {
          observer.unobserve(card);
        }
      });
    };
  }, []);

  return (
    <section id="services" className="py-20 bg-gray-100">
      <div 
        ref={sectionRef} 
        className="section-container opacity-0 transition-opacity duration-1000"
      >
        <div className="text-center mb-16">
          <div className="inline-flex items-center rounded-full px-3 py-1 text-sm font-medium bg-blue-100 text-blue-800 mb-6">
            <Syringe className="h-4 w-4 mr-1" />
            <span>Dịch vụ tiêm chủng</span>
          </div>
          <h2 className="text-balance font-bold text-3xl mb-6">
            Các gói <span className="text-blue-700">dịch vụ tiêm chủng</span> của chúng tôi
          </h2>
          <p className="text-gray-700 max-w-xl mx-auto">
            Chúng tôi cung cấp nhiều loại dịch vụ tiêm chủng phù hợp với nhu cầu đa dạng của khách hàng,
            bao gồm các gói tiêm trọn gói và tiêm lẻ, được thiết kế theo từng độ tuổi.
          </p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
          {services.map((service, index) => (
            <div
              key={service.id}
              ref={(el) => (cardsRef.current[index] = el)}
              className={`service-card bg-white shadow-lg rounded-xl p-6 opacity-0 transition-all duration-1000 delay-${index * 100} hover:shadow-xl border border-gray-200`}
              style={{ transitionDelay: `${index * 100}ms` }}
            >
              <div className={`${service.color} rounded-full w-12 h-12 flex items-center justify-center mb-4`}>
                <service.icon className={`h-6 w-6 ${service.iconColor}`} />
              </div>
              <h3 className="text-xl font-semibold mb-3 text-gray-800">{service.title}</h3>
              <p className="text-gray-600 mb-6">{service.description}</p>
              <Button variant="ghost" className="text-blue-700 hover:text-blue-800 hover:bg-blue-50 px-0">
                Xem chi tiết
              </Button>
            </div>
          ))}
        </div>

        <div className="mt-16 text-center">
          <Button className="bg-blue-700 hover:bg-blue-800 text-white px-6 py-2 rounded-md shadow-md">
            Xem tất cả dịch vụ
          </Button>
        </div>
      </div>
    </section>
  );
};

export default ServiceInfo;
