
import React, { useEffect, useRef } from 'react';
import { MapPin, Phone, Clock, ArrowRight } from 'lucide-react';
import { Button } from "@/components/ui/button";

const locations = [
  {
    id: 1,
    name: 'Phòng khám Trung tâm',
    address: '123 Đại lộ Sức khỏe, Quận Trung tâm',
    phone: '+84 (28) 123-4567',
    hours: 'Thứ 2-6: 8h-18h, Thứ 7: 9h-14h',
    image: 'https://images.unsplash.com/photo-1629136571045-05d5104ceb92?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=80',
  },
  {
    id: 2,
    name: 'Trung tâm Phía Tây',
    address: '456 Đường Y khoa, Quận Phía Tây',
    phone: '+84 (28) 987-6543',
    hours: 'Thứ 2-6: 9h-17h, Thứ 7: 10h-15h',
    image: 'https://images.unsplash.com/photo-1631248055158-edec7a3c072b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=80',
  },
  {
    id: 3,
    name: 'Trung tâm Phía Đông',
    address: '789 Đường Wellness, Quận Phía Đông',
    phone: '+84 (28) 456-7890',
    hours: 'Thứ 2-6: 8h-19h, Thứ 7: 9h-16h',
    image: 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=80',
  },
];

const LocationFinder = () => {
  const sectionRef = useRef<HTMLDivElement>(null);
  const locationRefs = useRef<(HTMLDivElement | null)[]>([]);

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

    locationRefs.current.forEach((location) => {
      if (location) {
        observer.observe(location);
      }
    });

    return () => {
      if (currentSectionRef) {
        observer.unobserve(currentSectionRef);
      }
      locationRefs.current.forEach((location) => {
        if (location) {
          observer.unobserve(location);
        }
      });
    };
  }, []);

  return (
    <section id="locations" className="py-20 bg-gradient-to-b from-white to-blue-50">
      <div 
        ref={sectionRef}
        className="section-container opacity-0 transition-opacity duration-1000"
      >
        <div className="text-center mb-16">
          <div className="inline-flex items-center rounded-full px-3 py-1 text-sm font-medium bg-blue-50 text-primary mb-6">
            <MapPin className="h-4 w-4 mr-1" />
            <span>Địa điểm của chúng tôi</span>
          </div>
          <h2 className="text-balance font-medium mb-6">
            Tìm <span className="text-primary">Trung tâm Tiêm chủng</span> gần bạn
          </h2>
          <p className="text-gray-600 max-w-xl mx-auto">
            Chúng tôi có nhiều địa điểm để phục vụ bạn tốt hơn. Mỗi trung tâm đều được trang bị 
            cơ sở vật chất hiện đại và nhân viên là những chuyên gia giàu kinh nghiệm.
          </p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          {locations.map((location, index) => (
            <div
              key={location.id}
              ref={(el) => (locationRefs.current[index] = el)}
              className="glass rounded-xl overflow-hidden shadow-sm opacity-0 transition-all duration-1000"
              style={{ transitionDelay: `${index * 100}ms` }}
            >
              <div className="h-48 overflow-hidden">
                <img 
                  src={location.image} 
                  alt={location.name} 
                  className="w-full h-full object-cover transition-transform duration-500 hover:scale-110"
                  loading="lazy"
                />
              </div>
              <div className="p-6">
                <h3 className="text-xl font-medium mb-3">{location.name}</h3>
                <div className="space-y-3 mb-6">
                  <div className="flex items-start">
                    <MapPin className="h-5 w-5 text-primary shrink-0 mt-0.5 mr-3" />
                    <span className="text-gray-600">{location.address}</span>
                  </div>
                  <div className="flex items-start">
                    <Phone className="h-5 w-5 text-primary shrink-0 mt-0.5 mr-3" />
                    <span className="text-gray-600">{location.phone}</span>
                  </div>
                  <div className="flex items-start">
                    <Clock className="h-5 w-5 text-primary shrink-0 mt-0.5 mr-3" />
                    <span className="text-gray-600">{location.hours}</span>
                  </div>
                </div>
                <Button variant="outline" className="w-full">
                  Đặt lịch tại địa điểm này
                  <ArrowRight className="ml-2 h-4 w-4" />
                </Button>
              </div>
            </div>
          ))}
        </div>

        <div className="mt-12 text-center">
          <Button className="bg-primary hover:bg-primary/90 text-white btn-hover-effect">
            Xem tất cả địa điểm
          </Button>
        </div>
      </div>
    </section>
  );
};

export default LocationFinder;
